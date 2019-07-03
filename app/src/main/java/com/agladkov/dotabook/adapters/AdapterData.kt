package com.agladkov.dotabook.adapters

interface AdapterData<T> {
    fun setData(items: List<T>)
}