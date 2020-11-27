package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var f:Int
    var l:Int
    init{
        f=0
        l=0
    }
    fun giveOperator(n:Int,ch:Char,d:Int):Int
    {
        if(ch=='+')
         return (d+n)
         else
            return (d-n)
    }
    fun giveNum(str:String):ArrayList<Int>{
        val num = "0123456789"
        var sign=""
        var sub:String=""
        var load=ArrayList<Int>()
        for(i in str.indices) {
            if (num.indexOf(str[i]) != -1) {
                sub += str[i]
            } else {
                load.add(sub.toInt())
                sub = ""
            }
        }
        return load
    }
    fun giveSign(str:String):String {
        val sig = "+-"
        var res: String = ""
        for (i in str.indices) {
            if (sig.indexOf(str[i]) != -1) {
                res = res + str[i]
            }
        }
        return res
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var str:String=""
        val v=findViewById<TextView>(R.id.txt)
        v.text="Your Calculation Here"
        val b1=findViewById<Button>(R.id.un)
        val b2=findViewById<Button>(R.id.two)
        val b3=findViewById<Button>(R.id.thr)
        val b4=findViewById<Button>(R.id.fur)
        val b5=findViewById<Button>(R.id.fiv)
        val b6=findViewById<Button>(R.id.six)
        val b7=findViewById<Button>(R.id.sev)
        val b8=findViewById<Button>(R.id.ate)
        val b9=findViewById<Button>(R.id.non)
        val b0=findViewById<Button>(R.id.zer)
        val bp=findViewById<Button>(R.id.plus)
        val bs=findViewById<Button>(R.id.sub)
        val be=findViewById<Button>(R.id.equ)
        val wrd=findViewById<TextView>(R.id.textView)
        wrd.text=""
        var f:Int
        var stn:String
        b1.text="1"
        b2.text="2"
        b3.text="3"
        b4.text="4"
        b5.text="5"
        b6.text="6"
        b7.text="7"
        b8.text="8"
        b9.text="9"
        b0.text="0"
        bp.text="+"
        bs.text="-"
        be.text="="
        b1.setOnClickListener{str=str+1
            v.text=str}
        b2.setOnClickListener{str=str+2
            v.text=str}
        b3.setOnClickListener{str=str+3
            v.text=str}
        b4.setOnClickListener{str=str+4
            v.text=str}
        b5.setOnClickListener{str=str+5
            v.text=str}
        b6.setOnClickListener{str=str+6
            v.text=str}
        b7.setOnClickListener{str=str+7
            v.text=str}
        b8.setOnClickListener{str=str+8
            v.text=str}
        b9.setOnClickListener{str=str+9
            v.text=str}
        b0.setOnClickListener{str=str+0
            v.text=str}
        bp.setOnClickListener{str=str+"+"
            v.text=str}
        bs.setOnClickListener{str=str+"-"
            v.text=str}
        be.setOnClickListener{
            stn=v.text as String
            var numbers=giveNum(stn)
            var sign=giveSign(stn)
            f = numbers[0]
            l = numbers[1]
            var cnt:Int=0
            for(i in numbers.indices) {
                f = giveOperator(f,sign[cnt++],l)
                l=  numbers[i+2]
            }
               v.text=f.toString()
                }
            }
        }


