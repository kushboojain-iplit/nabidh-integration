package org.bahmni.module.feedintegration.model;

import javax.persistence.*;

@Entity
@Table(name = "failedEvents")
public class FailedEvent {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "event_content")
    private String event;

    @Column(name = "feed_uri")
    private String feedUri;

    public String getFeedUri() {
        return feedUri;
    }

    public void setFeedUri(String feedUri) {
        this.feedUri = feedUri;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
