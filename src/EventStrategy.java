public interface EventStrategy {
    String execute(City city);
    EventType getEventType();
}
