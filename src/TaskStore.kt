class TaskStore {
    var state = TaskState(emptyList())
        private set

    fun dispatch(event: TaskEvent): Result<TaskState>{
        return TaskReducer().reduce(event, state).onSuccess { newState ->
            state = newState
        }
    }
}