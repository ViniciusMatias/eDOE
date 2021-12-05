package com.edoe.api.dto;

import com.edoe.api.entity.Descriptor;

import java.io.Serializable;

public class DescriptorDTO implements Serializable {
    private static final long serialVersionUID = 1275465251730198228L;

    private Long id;
    private String name;

    public DescriptorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DescriptorDTO(Descriptor descriptor) {
        this.id = descriptor.getId();
        this.name = descriptor.getName();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
