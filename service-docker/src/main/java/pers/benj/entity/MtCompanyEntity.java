package pers.benj.entity;

import javax.persistence.*;

@Entity
@Table(name = "mt_company", schema = "tarzan_common", catalog = "test")
public class MtCompanyEntity {
    private int id;
    private String code;
    private String info;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        MtCompanyEntity that = (MtCompanyEntity) o;

        if (id != that.id)
            return false;
        if (code != null ? !code.equals(that.code) : that.code != null)
            return false;
        if (info != null ? !info.equals(that.info) : that.info != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }
}
