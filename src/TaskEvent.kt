sealed interface TaskEvent {
    data class Add(val title: String) : TaskEvent
    data class Complete(val id: Int) : TaskEvent
    data class Remove(val id: Int) : TaskEvent
    object GetTasks: TaskEvent
}