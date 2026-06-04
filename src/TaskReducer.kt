import kotlin.collections.plus

class TaskReducer {
    fun reduce(event: TaskEvent, state: TaskState): Result<TaskState>{
        return runCatching {
            when(event){
                is TaskEvent.Add -> {
                    require(event.title.isNotBlank()) {"Title cannot be blank\n"}
                    val task = Task(title = event.title, id = state.tasks.size)
                    state.copy(tasks = state.tasks.plus(task))
                }
                is TaskEvent.Complete -> {
                    require(event.id in state.tasks.indices) {"There is no such task\n"}
                    val task = state.tasks.first { it.id == event.id }

                    state.copy(tasks = state.tasks.map{
                        if(it.id == event.id) it.copy(status = !task.status)
                        else it
                    })

                }
                is TaskEvent.Remove -> {
                    require(event.id in state.tasks.indices) {"There is no such task\n"}
                    state.copy(tasks = state.tasks.filter{ it.id != event.id })
                }
                TaskEvent.GetTasks -> state
            }
        }

    }
}