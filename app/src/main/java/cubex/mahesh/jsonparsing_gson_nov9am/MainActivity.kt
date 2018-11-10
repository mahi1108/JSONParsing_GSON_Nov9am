package cubex.mahesh.jsonparsing_gson_nov9am

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.gson.Gson
import cubex.mahesh.jsonparsing_gson_nov9am.beans.Employee
import cubex.mahesh.jsonparsing_gson_nov9am.beans.Employees
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileInputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        insert.setOnClickListener {
            var e = Employee(et1.text.toString().toInt(),
                        et2.text.toString(),et3.text.toString(),
                        et4.text.toString())
            var temp_list = mutableListOf<Employee>()
            temp_list.add(e)
            var emps = Employees(temp_list)
            var g = Gson( )
            var response:String = g.toJson(emps)
            var fos = openFileOutput("employees.json",
                    Context.MODE_PRIVATE)
            fos.write(response.toByteArray())
            fos.flush()
            fos.close()


        }
        read.setOnClickListener {

            var g = Gson( )
            var fis:FileInputStream =
                    openFileInput("employees.json")
            var emps = g.fromJson(InputStreamReader(fis),
                    Employees::class.java)
            var temp_list = mutableListOf<String>()
            for (emp in emps.employees)
            {
         temp_list.add(emp.id.toString() +" \t" + emp.name +"\n"+
                                emp.desig+"\t"+emp.dept)
            }
            var adapter = ArrayAdapter<String>(
                    this@MainActivity,
                    android.R.layout.simple_list_item_single_choice,
                    temp_list)
            lview.adapter = adapter
        }
    }
}
