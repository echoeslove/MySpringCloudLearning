package pers.benj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.shaded.curator.org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;
import org.apache.flink.util.Collector;

public class TopNSolution {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("127.0.0.1").build();

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "metric-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "latest");

        // 这个 kafka topic 需要和上面的工具类的 topic 一致
        // SingleOutputStreamOperator<Model> model =
        // env.addSource(new FlinkKafkaConsumer011<>("Model", new SimpleStringSchema(), props))
        // .setParallelism(1).map(string -> JSONObject.parseObject(string, Model.class));
        // model.timeWindowAll(Time.minutes(1)).apply(new AllWindowFunction<Model, List<Model>,
        // TimeWindow>() {
        // @Override
        // public void apply(TimeWindow window, Iterable<Model> values, Collector<List<Model>> out) throws
        // Exception {
        // ArrayList<Model> Models = Lists.newArrayList(values);
        // if (Models.size() > 0) {
        // System.out.println("1 分钟内收集到 Model 的数据条数是：" + Models.size());
        // out.collect(Models);
        // }
        // }
        // });

        FlinkKafkaConsumer011<String> kafkaConsumer011 =
                        new FlinkKafkaConsumer011<String>("Test", new SimpleStringSchema(), props);
        DataStream<String> stream = env.addSource(kafkaConsumer011);
        DataStream<Tuple2<String, Long>> redis = stream.flatMap(new LineSplitter()).keyBy(0).sum(1);
        redis.addSink(new RedisSink<Tuple2<String, Long>>(conf, new RedisExampleMapper()));

        env.execute("flink learning connectors kafka");
    }

    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Long>> {

        @Override
        public void flatMap(String s, Collector<Tuple2<String, Long>> collector) throws Exception {
            String[] tokens = s.toLowerCase().split("\\W+");
            System.out.println(Arrays.toString(tokens));
            for (String token : tokens) {
                if (StringUtils.isNotEmpty(token)) {
                    collector.collect(new Tuple2<String, Long>(token, 1L));
                }
            }
        }
    }

    public static class RedisExampleMapper implements RedisMapper<Tuple2<String, Long>> {

        @Override
        public RedisCommandDescription getCommandDescription() {
            return new RedisCommandDescription(RedisCommand.HSET, "HASH_NAME");
        }

        @Override
        public String getKeyFromData(Tuple2<String, Long> data) {
            return data.f0;
        }

        @Override
        public String getValueFromData(Tuple2<String, Long> data) {
            return data.f1.toString();
        }
    }
}
