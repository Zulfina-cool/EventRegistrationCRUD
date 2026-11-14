
package kz.iitu.event.registration.model;

// Model class for an event
public class EventInfo {

    private Integer id;
    private String title;
    private String location;

    // No-argument constructor
    public EventInfo() {
    }

    // Constructor with parameters
    public EventInfo(Integer id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
