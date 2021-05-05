package com.apress.quickpoll.domain;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="VOTE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="OPTION_ID")
    private Option option;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

//    ToString

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", option=" + option +
                '}';
    }
}
