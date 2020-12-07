package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var f: Int
    var l: Int
    init {
        f = 0
        l = 0
    }
    fun factorial(str: String):Int{
        var num=str.substring(0,str.length-2).toInt()
        var prod=1
        for(i in num downTo 1){
            prod*=i
        }
        return prod
    }
    fun giveFact(str: String):String{
        var s= " "+str+"  "
        var res=""
        var cur=""
        var st="+-x "
        var cnt=0
        do{
            while(true){
                cnt++
                cur+=s[cnt]
                if(st.indexOf(s[cnt])!=-1)
                    break
            }
            if(cur.indexOf('!')!=-1)
                res+=(factorial(cur).toString()+cur[cur.length-1])
            else
                res+=cur
            cur=""
        }while(cnt<s.length-2)
        return delSpace(res)

    }
    fun theProduct(str: String):String{
        var s= " "+str+"  "
        var res=""
        var cur=""
        var st="+- "
        var cnt=0
        do{
            while(true){
                cnt++
                cur+=s[cnt]
                if(st.indexOf(s[cnt])!=-1&&(s[cnt+1]!='x'&&s[cnt-1]!='x'))
                    break
             }
            if(cur.indexOf('x')!=-1)
               res+=(multiply(cur.substring(0,cur.length-1)).toString()+cur[cur.length-1])
           else
               res+=cur
            cur=""
        }while(cnt<s.length-2)
        return delSpace(res)
    }
    fun multiply(str:String):Int{
        var s=str+" "
        var t="0123456789-"
        var wrd=""
        var arrNum=ArrayList<Int>()
        for(i in 0..s.length-1){
            if(t.indexOf(s[i])!=-1) {
                wrd += s[i]
            }else
            {
                arrNum.add(wrd.toInt())
                wrd=""
            }
        }
        var prod=1
        for(i in arrNum.indices)
            prod*=arrNum[i]
        return prod
    }
    fun truMath(str: String): String {//copy @truthMath(String) \/
        val t = "+-"
        var c = 0
        var s = str + " "
        var nstr = ""
        for (i in 0..s.length - 2) {
            if (i == s.length - 2) break
            if (t.indexOf(s[i]) != -1 && t.indexOf(s[i + 1]) != -1) {
                c++
                nstr += s[i] + "" + s[i + 1]
                break
            }
        }
        if (c == 0) return "" else return nstr
    }

    fun ruleMath(s: String): Char {//Function returning a char '+'/'-' on the basis of the expression ;s="+-+.."(unsolved expression)// \/
        var c = 0
        for (i in 0..s.length - 1)
            if (s[i] == '-')
                c++
        if (c % 2 == 0) return '+' else return '-'
    }

    fun simulateMath(str: String): String {//function to get the unsolved operator series and the returns the resolved string free from any unsolved operators
        var nstr = str
        var ch: Char
        var flag = false
        while (true) {
            if (truMath(nstr) == "") break
            ch = ruleMath(truMath(nstr))
            if (repProd(str = nstr, c = ch, s = truMath(nstr)) == "") { //s not in str
                flag = true
                break
            }
            nstr = repProd(str = nstr, c = ch, s = truMath(nstr))
        }
        if (flag == true)
            return " "
        else
            return nstr

    }

    fun giveOperator(n: Int, ch: Char, d: Int): Int //function that takes the operands and the +/- as input and returns their +/-
    {
        if (ch == '+')
            return (d + n)
        else
            return (d - n)
    }

    fun giveNum(str: String): ArrayList<Int> { //function that seperates the numbers from the expressions and returns their arrayList
        val org = str + " " //the addition of ' ' is reason due to the for{ else { block; the elements will only be added to the list if the next element is not a number
        val num = "0123456789"
        var sub: String = ""
        var load = ArrayList<Int>() //empty list
        for (i in 0..org.length - 1) {
            if (num.indexOf(org[i]) != -1 || (i == 0 && (org[i] == '+' || org[i] == '-'))) {  //if it is a number
                sub += org[i]  //number creation
            } else {
                load.add(sub.toInt()) //submitting the number to the list
                sub = "" //reinitializing
            }
        }
        return load //returning complete list of the numbers in the expression
    }
    fun delSpace(s:String):String{
       var store=""
        for( i in s.indices)
        if(s[i]!=' ')
            store+=s[i]
        return store
    }
    fun giveSign(str: String): String { //function that returns the operator order from the expression via a string
        val sig = "+-"
        var res: String = ""
        for (i in str.indices) {
            if (sig.indexOf(str[i]) != -1) { //if it is a + or a -
                if (i == 0) continue //no use to check the first character b/c there may be a possibility of being a +/- sign there and also number being there wont affect the process afterall

                res = res + str[i]
            }
        }
        return res //returning order

    }

    fun repProd(str: String, s: String, c: Char): String { //Function finds s in str and replaces s with c \/
        var l = s.length
        var wrd = ""
        var flag = false
        var cnt = 0
        var inital: Int = 0
        var Final: Int = 0
        Out@ for (i in 0..str.length - l) {
            In@ while (cnt <= l - 1) {
                wrd = wrd + str[i + cnt]
                cnt++

            }
            if (wrd.equals(s)) {
                flag = true
                inital = i
                Final = i + l - 1
                break
            }
            cnt = 0
            wrd = ""
        }
        if (flag == true) {
            var new1 = ""
            var new2 = ""
            for (i in 0..inital - 1) //LOGIC-traversing expression forward ; loop to extract the all the expression till the staring number
            {
                new1 += str[i]
            }
            for (i in Final + 1..str.length - 1) //LOGIC-traversing expression backward ; loop to extract all the expression backwards till the ending number
            {
                new2 += str[i]// adding backwards
            }
            return (new1 + c + new2)
        } else
            return ""

    }

    fun repProd(str: String, inital: Int, Final: Int, Prod: Int): String { /*The function to replace the product expression with the actual product*/
        var new1 = ""
        var new2 = ""
        for (i in 0..inital - 1) //LOGIC-traversing expression forward ; loop to extract the all the expression till the staring number
        {
            new1 += str[i]
        }
        for (i in Final + 1..str.length - 1) //LOGIC-traversing expression backward ; loop to extract all the expression backwards till the ending number
        {
            new2 += str[i]// adding backwards
        }
        return (new1 + Prod.toString() + new2) //returning expression with product instead of the product expression
    }

    val start: (String) -> Boolean = { s -> s.indexOf('+') > s.indexOf('x') || s.indexOf('-') > s.indexOf('x') }/*lambda to check if a 'x' operator comes before '+'*/
    val end: (String) -> Boolean = { s -> s.lastIndexOf('+') < s.lastIndexOf('x') || s.lastIndexOf('-') < s.lastIndexOf('x') }/*lambda to check if 'x' comes after the '+' in the end*/

    fun giveProdctOutDated(str: String): String { //function that returns expression free of product operators//
        var newword: String = str  //to store the current and the expression free of product operator//
        var cur: Int
        var boolp = false
        var s = "0123456789"
        val t = "0123456789+-"
        var bool = false
        var fir = ""
        var las = ""
        var i = newword.indexOf('x')
        if (start(str))
            newword = " " + newword //adding space to prevent program crash
        if (end(str))
            newword = newword + " "
        Outer@ while (true) {  /*loop that is used to set the target on 'x'(product)
                                                LOGIC- loop executes till the 'x' exists in the expression
                                                LABELLING IS USED*/

            i = newword.indexOf('x')
            if (i == -1)
                break
            var cnt = i + 1  //the counter of the the second number
            var cnt2 = i - 1  //the counter of the first number
            if (newword[i + 1] == '-') {
                bool = true
                cnt++
            }
            if (newword[i + 1] == '+') {
                boolp = true
                cnt++
            }
            Inner@ while (s.indexOf(newword[cnt]) != -1) { /*loop to extract the number after 'x'
                                                  LOGIC- loop executes till the next character in the expression is a number*/
                las += newword[cnt]
                cnt++ //update
            }
            if (bool == true)
                las = "-" + las
            if (boolp == true)
                las = "+" + las
            while (s.indexOf(newword[cnt2]) != -1) { /*loop to extract number befor 'x'
                                               LOGIC- loop executes till the previous characters in the expression are numbers*/
                fir = newword[cnt2] + fir //loop runs backward so reverse addition of characters
                cnt2--  //update
            }
            cur = (fir.toInt()) * (las.toInt())      //the product
            var one = cnt2 + 1
            var end = cnt - 1
            newword = repProd(inital = one/*initial index of exp*/, Final = end/*final index of the exp*/, Prod = cur/*product*/, str = newword/*the current word updatingto be the word without 'x'*/)
            Log.e(newword + " " + fir + " " + " " + las + " " + i + " " + one + " " + end + " ", "record")
            fir = ""
            las = ""
        }
        if (start(str))
            return simulateMath(newword.substring(1, newword.length)) //removing space
        if (end(str))
            return simulateMath(newword.substring(0, newword.length - 1))
        if (start(str) && end(str))
            t + return simulateMath(newword.substring(1, newword.length - 1))
        return newword /*free from 'x' expressions */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(giveFact("5!+4!+3!").toString(),"test04")
        var str: String = ""
        val v = findViewById<TextView>(R.id.txt) //creation of the display screen in the calculator
        v.text = "Your Calculation Here"
        val b1 = findViewById<Button>(R.id.un) /*creation of 0..9 number buttons and +-* buttons  */
        val b2 = findViewById<Button>(R.id.two)
        val b3 = findViewById<Button>(R.id.thr)
        val b4 = findViewById<Button>(R.id.fur)
        val b5 = findViewById<Button>(R.id.fiv)
        val b6 = findViewById<Button>(R.id.six)
        val b7 = findViewById<Button>(R.id.sev)
        val b8 = findViewById<Button>(R.id.ate)
        val b9 = findViewById<Button>(R.id.non)
        val b0 = findViewById<Button>(R.id.zer)
        val bp = findViewById<Button>(R.id.plus)
        val bs = findViewById<Button>(R.id.sub)
        val bm = findViewById<Button>(R.id.into)
        val bd = findViewById<Button>(R.id.div)
        val bf = findViewById<Button>(R.id.dot)
        val be = findViewById<Button>(R.id.equ)
        val bc = findViewById<Button>(R.id.clr)
        val bca = findViewById<Button>(R.id.clral)
        val bb1 = findViewById<Button>(R.id.ob)
        val bb2 = findViewById<Button>(R.id.cb)
        val bfc = findViewById<Button>(R.id.fac)
        var f: Int
        var stn: String
            b1.setOnClickListener {
                str = str + 1 /*when pressed the number button 0..9 + or - or * or / , display of the input thus creation of the expression*/
                v.text = str
            }
            b2.setOnClickListener {
                str = str + 2
                v.text = str
            }
            b3.setOnClickListener {
                str = str + 3
                v.text = str
            }
            b4.setOnClickListener {
                str = str + 4
                v.text = str
            }
            b5.setOnClickListener {
                str = str + 5
                v.text = str
            }
            b6.setOnClickListener {
                str = str + 6
                v.text = str
            }
            b7.setOnClickListener {
                str = str + 7
                v.text = str
            }
            b8.setOnClickListener {
                str = str + 8
                v.text = str
            }
            b9.setOnClickListener {
                str = str + 9
                v.text = str
            }
            b0.setOnClickListener {
                str = str + 0
                v.text = str
            }
            bp.setOnClickListener {
                str = str + "+"
                v.text = str
            }
            bs.setOnClickListener {
                str = str + "-"
                v.text = str
            }
            bm.setOnClickListener {

                str = str + "x"
                v.text = str
            }
            bd.setOnClickListener {
                str = str + "/"
                v.text = str
            }
            bc.setOnClickListener {
                str = str.substring(0, str.length - 1)
                v.text = str
            }
            bca.setOnClickListener {
                str = ""
                v.text = str
            }
            bfc.setOnClickListener {
                str= str + "!"
                v.text = str
            }
            be.setOnClickListener { /*when pressed = , the execution of the maths*/
                var temp="+-"
                var stn: String = v.text as String //stn is the expression from a string objrct to string
                stn = simulateMath(stn)//refining the expression by mathematical logic
                if(stn.indexOf('!')!=-1){
                    stn=giveFact(stn)
                }
                if (stn.indexOf('x') != -1) {  //clearing out product first
                    stn = theProduct(stn)
                }
                if(stn.indexOf('+')==-1||stn.indexOf('-')==-1) {
                    stn=stn+"+0"
                }

                var num = giveNum(stn) //list
                var seq = giveSign(stn) // sequence
                var c = 0 //counter for the operand order
                var t = 1 //counter for 2nd targeter
                f = num[0] //targeting first element
                l = num[t++] //targeting second element and above
                for (i in 0..num.size - 2) {  /*running loop from 0 to the one element less*/
                    f = giveOperator(l, seq[c++], f) // updating f to sum/diff of f and l
                    if (i == num.size - 2) //since there wont be any element present after in the list after this stage//
                        break
                    l = num[t++]
                }
                v.text = f.toString() // f contains the resolved output ; thus displaying on the SCREEN

            }

    }
}





