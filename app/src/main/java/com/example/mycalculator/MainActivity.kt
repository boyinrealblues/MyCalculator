package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.util.Log.e as logE

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
            return (n-d)
    }
    fun giveNum(str:String):ArrayList<Int>{
        val org=str+" "
        val num = "0123456789"
        var sub:String=""
        var load=ArrayList<Int>()
        for(i in 0..org.length-1) {
            if (num.indexOf(org[i]) != -1) {
                sub += org[i]
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
            var stn:String=v.text as String
            var num=giveNum(stn)
            var seq=giveSign(stn)
            var c=0
            var t=1
            f=num[0]
            l=num[t++]
            for(i in 0..num.size-2){
                f=giveOperator(l,seq[c++],f)
                if(i==num.size-2)
                    break
                l=num[t++]
            }
            v.text=f.toString()
}
        Log.e(giveOperator(4,'-',6).toString(),"Hello")
        Log.e(giveNum("7+8+9+5+4+6").toString(),"hell")
        Log.e(giveSign("4+8+7-8").toString(),"end")
    }

}


