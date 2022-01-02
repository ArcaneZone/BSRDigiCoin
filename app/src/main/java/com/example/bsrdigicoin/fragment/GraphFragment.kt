package com.example.bsrdigicoin.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bsrdigicoin.R
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter

import com.jjoe64.graphview.series.LineGraphSeries

import com.jjoe64.graphview.series.DataPoint
import java.util.*



class GraphFragment : Fragment() {
    private  val mHandler = Handler()
    private lateinit var  mTimer1:Runnable
    lateinit var mSeries1:LineGraphSeries<DataPoint>
    private var graph2LastXValue:Double = 1.0;
    private lateinit var graph:GraphView
    var max=29.00;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_graph, container, false)
        graph= view.findViewById(R.id.graph)
        val calendar = GregorianCalendar.getInstance()
        val d1 = calendar.time
        calendar.add(Calendar.DATE, -30)
        val d2 = calendar.time
        calendar.add(Calendar.DATE,30)
        val d3 = calendar.time


        mSeries1 = LineGraphSeries()
        graph.addSeries(mSeries1)
        graph.viewport.isXAxisBoundsManual = true

        // set date label formatter
       // graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(activity)
        //graph.gridLabelRenderer.numHorizontalLabels = 15 // only 4 because of the space
        graph.viewport.setMinX(1.0)
        graph.viewport.setMaxX(15.0)
        graph.viewport.setMaxY(33.0)
        graph.viewport.setMinY(26.00)

       // graph.viewport.isXAxisBoundsManual = true;
       // graph.getGridLabelRenderer().setHumanRounding(false);

        return  view
    }
//    override fun onAttach(activity: Activity) {
//        super.onAttach(activity)
//        (activity as MainActivity).onSectionAttached(
//            requireArguments().getInt(MainActivity.ARG_SECTION_NUMBER)
//        )
//    }

    override fun onResume() {
        super.onResume()
        mTimer1 = object : Runnable {
            override fun run() {
                graph2LastXValue += 1.0
                val datapointNow:DataPoint=generateSingleNextPoint(graph2LastXValue, max)
                mSeries1.appendData(datapointNow,true,15)
                max=datapointNow.y
                mHandler.postDelayed(this, 1000)
            }
        }
        mHandler.postDelayed(mTimer1, 1000)
    }

    override fun onPause() {
        mHandler.removeCallbacks(mTimer1)
        super.onPause()
    }

    private fun generateData(): Array<DataPoint?>? {
        val count = 30
        val calendar = Calendar.getInstance()
        val d1 = calendar.time
        calendar.add(Calendar.DATE, 1)
        val values = arrayOfNulls<DataPoint>(count)
        val arr= arrayListOf<Double>(
            0.30,0.15,0.25,0.005,-0.30,-0.25,-0.15,-0.005
        )
        var y:Double=30.00
        for (i in 0 until count) {
            val d = calendar.time
            calendar.add(Calendar.DATE, i+1)
            val random = arr.random()
            y += random
            val v = DataPoint(d, y)
            values[i] = v
        }
        return values
    }
    fun generateSingleNextPoint(x:Double,y:Double):DataPoint{
        val arr= arrayListOf<Double>(
            0.30,0.15,0.25,0.005,-0.30,-0.25,-0.15,-0.005
        )
        val random = arr.random()
        return DataPoint(x+1,y+random)
    }




}