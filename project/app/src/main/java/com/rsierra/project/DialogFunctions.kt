package com.rsierra.project

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.RadioButton
import com.rsierra.project.database.entity.Task
import com.rsierra.project.databinding.EditDialogLayoutBinding


fun createEditTaskDialog(context: Context, task: Task,
                         update: (Task) -> Unit) {
    val dialogBinding = EditDialogLayoutBinding.inflate(LayoutInflater.from(context))
    val dialog = AlertDialog.Builder(context)
    dialogBinding.title.setText(task.title)
    dialogBinding.description.setText(task.description)
    dialogBinding.radioGroupPriority.check(task.priority)
    dialog.setTitle("Modificar task")
    dialog.setView(dialogBinding.root)
    dialog.setPositiveButton("EDITAR ") { d, _ ->
        val updatedTask  = Task(
            id = task.id,
            title = dialogBinding.title.text.toString(),
            description = dialogBinding.description.text.toString(),
            priority = getPriorityPosition(dialogBinding)
        )
        update(updatedTask)
    }
    dialog.setNeutralButton("CANCELAR") { d, _ ->
        d.dismiss()
    }
    dialog.show()
}
fun createTaskDialog(context: Context, task: Task,
                     create: (Task) -> Unit,
                     update: (Task) -> Unit,
                     delete: (Task) -> Unit,
                     detail: (Task) -> Unit) {
    val dialogBinding = EditDialogLayoutBinding.inflate(LayoutInflater.from(context))
    val dialog = AlertDialog.Builder(context)
    if (task.id != 0) {
        dialog.setPositiveButton("EDITAR ") { d, _ ->

            update(task)
        }
        dialog.setNegativeButton("COMPLETADA ") { d, _ ->
            delete(task)
        }
        dialog.setNeutralButton("Detalle") { d, _ ->
            detail(task)
        }
    } else {
        dialog.setTitle("Crear task")
        dialog.setView(dialogBinding.root)
        dialog.setPositiveButton("Crear") { d, _ ->
            val newTask  = Task(
                title = dialogBinding.title.text.toString(),
                description = dialogBinding.description.text.toString(),
                priority = getPriorityPosition(dialogBinding)
            )
            create(newTask)
        }
        dialog.setNeutralButton("CANCELAR") { d, _ ->
            d.dismiss()
        }
    }

    dialog.show()
}

fun getPriorityPosition(selectedRadioButton: EditDialogLayoutBinding): Int {
    val selectedRadioButtonId = selectedRadioButton.radioGroupPriority.checkedRadioButtonId
    var priorityPosition = -1

    // Find the position of the selected RadioButton in the RadioGroup
    for (i in 0 until selectedRadioButton.radioGroupPriority.childCount) {
        val radioButton = selectedRadioButton.radioGroupPriority.getChildAt(i) as RadioButton
        if (radioButton.id == selectedRadioButtonId) {
            priorityPosition = i
            break
        }
    }

    return priorityPosition;
}