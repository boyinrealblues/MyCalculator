package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var f:Int
    var l:Int
    init{
        f=0
        l=0
    }
    fun giveOperator(n:Int,ch:Char,d:Int):Int //function that takes the operands and the +/- as input and returns their +/-
    {
        if(ch=='+')
         return (d+n)
         else
            return (d-n)
    }
    fun giveNum(str:String):ArrayList<Int>{ //function that seperates the numbers from the expressions and returns their arrayList
        val org=str+" " //the addition of ' ' is reason due to the for{ else { block; the elements will only be added to the list if the next element is not a number
        val num = "0123456789"
        var sub:String=""
        var load=ArrayList<Int>() //empty list
        for(i in 0..org.length-1) {
            if (num.indexOf(org[i]) != -1||(i==0&&(org[i]=='+'||org[i]=='-'))) {  //if it is a number
                sub += org[i]  //number creation
            } else {
                load.add(sub.toInt()) //submitting the number to the list
                sub = "" //reinitializing
            }
        }
        return load //returning complete list of the numbers in the expression
    }

    fun giveSign(str:String):String { //function that returns the operator order from the expression via a string
        val sig = "+-"
        var res: String = ""
        for (i in str.indices) {
            if (sig.indexOf(str[i]) != -1) { //if it is a + or a -
                if(i==0) continue //no use to check the first character b/c there may be a possibility of being a +/- sign there and also number being there wont affect the process afterall

                res = res + str[i]
            }
        }
        return res //returning order

    }
    fun repProd(str: String,inital:Int,Final:Int,Prod:Int):String{ /*The function to replace the product expression with the actual product*/
        var new1=""
        var new2=""
        for(i in 0..inital-1) //LOGIC-traversing expression forward ; loop to extract the all the expression till the staring number
        {
            new1+=str[i]
        }
        for(i in Final+1..str.length-1) //LOGIC-traversing expression backward ; loop to extract all the expression backwards till the ending number
        {
            new2+=str[i]// adding backwards
        }
        return (new1+Prod.toString()+new2) //returning expression with product instead of the product expression
    }
    fun giveProduct(str:String):String{ //function that returns expression free of product operators//
        var newword:String=str  //to store the current and the expression free of product operator//
        var cur:Int
        var s="0123456789"
        val t="0123456789+-"
        var fir=""
        var las=""
        var i = newword.indexOf('x')
        while (true) {  /*loop that is used to set the target on 'x'(product)
                                                LOGIC- loop executes till the 'x' exists in the expression */
            i=newword.indexOf('x')
            if(i==-1)
                break
            Log.e(i.toString(),"hello3")
            var cnt = i + 1  //the counter of the the second number
            var cnt2=i-1  //the counter of the first number
            while (s.indexOf(newword[cnt]) != -1) { /*loop to extract the number after 'x'
                                                  LOGIC- loop executes till the next character in the expression is a number*/
                las += newword[cnt]
                cnt++ //update
                Log.e(cnt.toString(),"hello2")
            }
            while(s.indexOf(newword[cnt2])!=-1){ /*loop to extract number befor 'x'
                                               LOGIC- loop executes till the previous characters in the expression are numbers*/
                fir=newword[cnt2]+fir //loop runs backward so reverse addition of characters
                cnt2--  //update
                Log.e(cnt2.toString(),"hello")
            }
             cur=(fir.toInt())*(las.toInt())      //the product
            var one=cnt2+1
            var end=cnt-1
             newword=repProd(inital=one/*initial index of exp*/,Final=end/*final index of the exp*/,Prod = cur/*product*/,str=newword/*the current word updatingto be the word without 'x'*/)
            Log.e(newword+" "+fir+" "+" "+las+" "+i+" "+one+" "+end+" ","record")
        }
         return newword /*free from 'x' expressions */

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(giveProduct("1+2x3+4"),"this is a message")
        var str:String=""
        val v=findViewById<TextView>(R.id.txt) //creation of the display screen in the calculator
        v.text="Your Calculation Here"
        val b1=findViewById<Button>(R.id.un) /*creation of 0..9 number buttons and +-* buttons  */
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
        val bm=findViewById<Button>(R.id.into)
        val bd=findViewById<Button>(R.id.div)
        val bf=findViewById<Button>(R.id.dot)
        val be=findViewById<Button>(R.id.equ)
        var f:Int
        var stn:String
        b1.text="1" /*giving the display names to 0..9 number buttons*/
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
        b1.setOnClickListener{str=str+1 /*when pressed the number button 0..9 + or - or * or / , display of the input thus creation of the expression*/
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
        bm.setOnClickListener {str=str+"x"
        }
        bd.setOnClickListener {str=str+"/"
        }
        be.setOnClickListener{ /*when pressed = , the execution of the maths*/
            var stn:String=v.text as String    //stn is the expression from a string objrct to string
            if(stn.indexOf('x')!=-1){  //clearing out product first
                stn=giveProduct(stn)
            }
            var num=giveNum(stn) //list
            var seq=giveSign(stn) // sequence
            var c=0 //counter for the operand order
            var t=1 //counter for 2nd targeter
            f=num[0] //targeting first element
            l=num[t++] //targeting second element and above
            for(i in 0..num.size-2){  /*running loop from 0 to the one element less*/
                f=giveOperator(l,seq[c++],f) // updating f to sum/diff of f and l
                if(i==num.size-2) //since there wont be any element present after in the list after this stage//
                    break
                l=num[t++]
            }
            v.text=f.toString() // f contains the resolved output ; thus displaying on the SCREEN

}
    }

}