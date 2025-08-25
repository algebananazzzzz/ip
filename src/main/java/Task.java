abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;  // <-- enum here

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public boolean isDone() { return isDone; }

    public String getDescription() { return description; }

    public TaskType getType() { return type; }

    @Override
    public String toString() {
        return "[" + type.getIcon() + "][" + getStatusIcon() + "] " + description;
    }
}
