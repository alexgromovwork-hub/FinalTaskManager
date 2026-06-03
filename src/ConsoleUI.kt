import kotlin.system.exitProcess

class ConsoleUI {
    fun start(){
        println("Hello. It is a task manager app. Choose one of actions below to proceed or quit")
        while(true){
            var event: TaskEvent? = null
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
                else -> continue
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
            var Num = readln()
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
            "1" -> return TaskEvent.Complete(taskNum)
            "2" -> return TaskEvent.Remove(taskNum)
            else -> {println("Wrong input. Try again")
            continue}
        }
        }

    }
}