import kotlin.collections.plus

class TaskReducer {
    fun reduce(event: TaskEvent, state: TaskState): Result<TaskState>{
        return runCatching {
            when(event){
                is TaskEvent.Add -> {
                    require(event.title.isNotBlank())
                    val task = Task(title = event.title, id = state.tasks.size)
                    state.copy(tasks = state.tasks.plus(task))
                }
                is TaskEvent.Complete -> {
                    val task = state.tasks.first { it.id == event.id }

                    state.copy(tasks = state.tasks.map{
                        if(it.id == event.id) it.copy(status = task.status)
                        else it
                    })

                }
                is TaskEvent.Remove -> {
                    state.copy(tasks = state.tasks.filter{ it.id != event.id })
                }
                TaskEvent.GetTasks -> state
            }
        }

    }
}