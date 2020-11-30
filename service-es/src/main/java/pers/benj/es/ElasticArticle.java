package pers.benj.es;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "blog", type = "item", useServerConfiguration = true, createIndex = false)
public class ElasticArticle implements Serializable {
    private static final long serialVersionUID = 2145237608643611164L;

    @Id // org.springframework.data.annotation.Id
    private Long id; // 文章ID

    // 指定字段对应的ES类型是Text，analyzer指定分词器为ik_max_word
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String author; // 文章作者

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title; // 文章标题

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content; // 文章正文内容

    // 指定字段对应ES中的类型是Date，使用自定义的日期格式化，pattern指定格式化
    // 规则是“日期时间”或“日期”或“时间毫秒”
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date createTime; // 文章创建时间

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date updateTime; // 文章更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        if (createTime == null) {
            return null;
        } else {
            return (Date) createTime.clone();
        }
    }

    public void setCreateTime(Date createTime) {
        if (createTime == null) {
            this.createTime = null;
        } else {
            this.createTime = (Date) createTime.clone();
        }
    }

    public Date getUpdateTime() {
        if (updateTime == null) {
            return null;
        } else {
            return (Date) updateTime.clone();
        }
    }

    public void setUpdateTime(Date updateTime) {
        if (updateTime == null) {
            this.updateTime = null;
        } else {
            this.updateTime = (Date) updateTime.clone();
        }
    }
}
