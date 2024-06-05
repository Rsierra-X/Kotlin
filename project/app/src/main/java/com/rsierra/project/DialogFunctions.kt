package com.rsierra.project

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.rsierra.project.database.entity.Task
import com.rsierra.project.databinding.EditDialogLayoutBinding

/*fun deleteCalculoDialog(context: Context,
                        calculo: Calculo,
                        borrar: (Calculo) -> Unit
) {
    val dialogo = AlertDialog.Builder(context)
    dialogo.setTitle(context.resources.getString(R.string.delete_calculo_titulo))
    dialogo.setMessage("Eliminar ${calculo.operator_1} ${calculo.operator} ${calculo.operator_2}?")
    dialogo.setPositiveButton("BORRAR") { d, _ ->
        borrar(calculo)
    }
    dialogo.setNeutralButton("CANCELAR") {d, _ ->
        d.dismiss()
    }
    dialogo.show()
}*/

fun createTaskDialog(context: Context, task: Task, create: (Task) -> Unit, update: (Task) -> Unit) {
    val dialogBinding = EditDialogLayoutBinding.inflate(LayoutInflater.from(context))
    val dialog = AlertDialog.Builder(context)
    if (task.id != 0) {
        dialogBinding.title.setText(task.title)
        dialogBinding.description.setText(task.description)
        dialogBinding.radioGroupPriority.check(task.priority)
        dialog.setTitle("Modificar task")
        dialog.setView(dialogBinding.root)
        dialog.setPositiveButton("Actualizar") { d, _ ->
            val updatedTask  = Task(
                id = task.id,
                title = dialogBinding.title.text.toString(),
                description = dialogBinding.description.text.toString(),
                priority = dialogBinding.radioGroupPriority.checkedRadioButtonId
            )
            update(updatedTask)
        }
    } else {
        dialog.setTitle("Crear task")
        dialog.setView(dialogBinding.root)
        dialog.setPositiveButton("Crear") { d, _ ->
            val newTask  = Task(
                title = dialogBinding.title.text.toString(),
                description = dialogBinding.description.text.toString(),
                priority = dialogBinding.radioGroupPriority.checkedRadioButtonId
            )
            create(newTask)
        }
    }
    dialog.setNegativeButton("CANCELAR") { d, _ ->
        d.dismiss()
    }
    dialog.show()
}