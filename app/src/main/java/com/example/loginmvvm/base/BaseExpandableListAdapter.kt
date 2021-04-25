//package com.example.loginmvvm.base
//
//import android.database.DataSetObservable
//import android.database.DataSetObserver
//import android.widget.ExpandableListAdapter
//import android.widget.HeterogeneousExpandableList
//
//abstract class BaseExpandableListAdapter : ExpandableListAdapter, HeterogeneousExpandableList {
//    private val mDataSetObservable = DataSetObservable()
//    override fun registerDataSetObserver(observer: DataSetObserver) {
//        mDataSetObservable.registerObserver(observer)
//    }
//
//    override fun unregisterDataSetObserver(observer: DataSetObserver) {
//        mDataSetObservable.unregisterObserver(observer)
//    }
//
//
//    fun notifyDataSetInvalidated() {
//        mDataSetObservable.notifyInvalidated()
//    }
//
//
//    fun notifyDataSetChanged() {
//        mDataSetObservable.notifyChanged()
//    }
//
//    override fun areAllItemsEnabled(): Boolean {
//        return true
//    }
//
//    override fun onGroupCollapsed(groupPosition: Int) {}
//    override fun onGroupExpanded(groupPosition: Int) {}
//
//
//    override fun getCombinedChildId(groupId: Long, childId: Long): Long {
//        return (-0x8000000000000000L or (groupId and 0x7FFFFFFF shl 32).toInt() or (childId and -0x1).toInt()).toLong()
//    }
//
//
//    override fun getCombinedGroupId(groupId: Long): Long {
//        return groupId and 0x7FFFFFFF shl 32
//    }
//
//
//    override fun isEmpty(): Boolean {
//        return groupCount == 0
//    }
//
//
//    override fun getChildType(groupPosition: Int, childPosition: Int): Int {
//        return 0
//    }
//
//
//    override fun getChildTypeCount(): Int {
//        return 1
//    }
//
//
//    override fun getGroupType(groupPosition: Int): Int {
//        return 0
//    }
//
//
//    override fun getGroupTypeCount(): Int {
//        return 1
//    }
//}