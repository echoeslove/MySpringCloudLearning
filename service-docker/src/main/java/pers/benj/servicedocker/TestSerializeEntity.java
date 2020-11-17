package pers.benj.servicedocker;

import java.io.Serializable;

public class TestSerializeEntity implements Serializable {
    private static final long serialVersionUID = 1967440644808365899L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
