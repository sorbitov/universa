/*
 * Created by Maxim Pogorelov <pogorelovm23@gmail.com>, 10/26/17.
 */


package com.icodici.universa.contract;

import net.sergeych.biserializer.BiDeserializer;
import net.sergeych.biserializer.BiSerializable;
import net.sergeych.biserializer.BiSerializer;
import net.sergeych.biserializer.DefaultBiMapper;
import net.sergeych.tools.Binder;

import java.util.ArrayList;
import java.util.List;


public class Reference implements BiSerializable {

    private String name;

    private List<String> roles;
    private String origin;
    private List<String> fields;

    public Reference() {
        this.roles = new ArrayList<>();
        this.fields = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Reference setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Reference addRole(String role) {
        this.roles.add(role);
        return this;
    }

    public Reference setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public Reference setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public List<String> getFields() {
        return fields;
    }

    public Reference addField(String field) {
        this.fields.add(field);
        return this;
    }

    public Reference setFields(List<String> fields) {
        this.fields = fields;
        return this;
    }

    @Override
    public void deserialize(Binder data, BiDeserializer deserializer) {

        this.name = data.getString("name", null);

        this.origin = data.getString("origin", null);

        List<String> roles = data.getList("roles", null);
        if (roles != null) {
            this.roles.clear();
            roles.forEach(this::addRole);
        }

        List<String> fields = data.getList("fields", null);
        if (fields != null) {
            this.fields.clear();
            fields.forEach(this::addField);
        }


    }

    @Override
    public Binder serialize(BiSerializer s) {
        return Binder.fromKeysValues(
                "name", s.serialize(this.name),
                "roles", s.serialize(this.roles),
                "origin", s.serialize(this.origin),
                "fields", s.serialize(this.fields));
    }

    static {
        DefaultBiMapper.registerClass(Reference.class);
    }
}
