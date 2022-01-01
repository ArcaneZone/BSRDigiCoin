package com.example.bsrdigicoin.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bsrdigicoin.R
import com.jjoe64.graphview.GraphView

import com.jjoe64.graphview.series.LineGraphSeries

import com.jjoe64.graphview.series.DataPoint
import java.util.*



class GraphFragment : Fragment() {
    private  val mHandler = Handler();
    lateinit var  mTimer1:Runnable
    lateinit var  mTimer2:Runnable
    lateinit var mSeries1:LineGraphSeries<DataPoint>
    private var graph2LastXValue:Double = 5.0;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_graph, container, false)

        val graph:GraphView= view.findViewById(R.id.graph)
        mSeries1 = LineGraphSeries(generateData())
        view.findViewById<GraphView>(R.id.graph).addSeries(mSeries1)
        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.setMinX(0.0)
        graph.viewport.setMaxX(40.0)

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
                mSeries1.resetData(generateData())
                mHandler.postDelayed(this, 300)
            }
        }
        mHandler.postDelayed(mTimer1, 300)

    }

    override fun onPause() {
        mHandler.removeCallbacks(mTimer1)
        super.onPause()
    }

    private fun generateData(): Array<DataPoint?>? {
        val count = 30
        val values = arrayOfNulls<DataPoint>(count)
        val arr= arrayListOf<Double>(
            0.30,0.15,0.25,0.005,-0.30,-0.25,-0.15,-0.005
        )
        var y:Double=30.00
        for (i in 0 until count) {
            val random = arr.random()
            y += random
            val x= Calendar.getInstance().time
            val v = DataPoint(x, y)
            values[i] = v
        }
        return values
    }




}