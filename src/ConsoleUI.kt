import kotlin.system.exitProcess

class ConsoleUI {
    fun start(){
        val store = TaskStore()
        println("Hello. It is a task manager app. Choose one of actions below to proceed or quit")
        lateinit var event: TaskEvent
        while(true){

            println("1. Show all your tasks \n2. Add new task \n3. Select task to delete or change status\n4. Exit app")
            val answer = readln()
            when (answer) {
                "1" -> event = TaskEvent.GetTasks
                "2" -> event = TaskEvent.Add(newTask())
                "3" -> event = chooseFun()
                "4" -> {
                    println("Bye!")
                    exitProcess(0)
                }
                else -> {
                    println("Wrong input. Try again")
                    continue
                }
            }
            if(event is TaskEvent.GetTasks) {
                println("Here is your task list:")
                store.dispatch(event).onSuccess { state ->
                    state.tasks.forEach { task ->
                        println("${task.id+1}. ${task.title} | ${if(!task.status) "TO DO" else "DONE"}")
                    }
                }
                println()
            }else
            store.dispatch(event).onSuccess{
                println("Done!\n")
            }.onFailure {
                println(it.message)
            }

        }

    }
    fun newTask(): String{
        println("Enter your task:")
        return readln()
    }
    fun chooseFun(): TaskEvent{
        println("Enter task number:")
        var taskNum: Int
        while (true){
            val Num = readln()
            if (Num.toIntOrNull() == null){
                println("Wrong input. Try again")
                continue
            } else {
                taskNum = Num.toInt()
                break
            }
        }
        println("Enter chosen action: \n1. Complete task \n2. Delete task")
        var act: String
        while (true){
            act = readln()
        when (act){
            "1" -> return TaskEvent.Complete(taskNum-1)
            "2" -> return TaskEvent.Remove(taskNum-1)
            else -> {println("Wrong input. Try again")
            continue}
        }
        }

    }
}