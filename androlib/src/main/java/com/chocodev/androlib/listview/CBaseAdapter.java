package com.chocodev.androlib.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by mobivery on 19/09/13.
 */
public class CBaseAdapter<T, Q extends BindableView<T>> extends BaseAdapter {

    private Class viewClass;
    private Class objectClass;
    private List<T> items;
    private ListEventListener listEventListener;
    private Method builderMethod=null;

    public CBaseAdapter(Class<T> objectClass, Class<Q> viewClass, List<T> items) {
        this.objectClass = objectClass;
        this.viewClass = viewClass;
        this.items = items;
        try
        {
            builderMethod=viewClass.getMethod("build",Context.class);
        }
        catch(Exception ex)
        {

        }
    }

    /**
     * Change the list objects to items
     * @param items
     */
    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();

    }

    /**
     * Clear all items from the list
     */
    public void clearItems() {
        this.items.clear();
        notifyDataSetChanged();
    }

    /**
     * Add new items to the existing ones
     * @param items
     */
    public void addItems(List<T> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return items == null ? null : items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BindableView<T> viewGroup = (BindableView<T>) convertView;
        if (viewGroup == null) {

            if(builderMethod==null)
            {
            // has no build
            try {
                Constructor constructor = viewClass.getConstructor(Context.class);
                viewGroup = (BindableView<T>) constructor.newInstance(parent.getContext());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            }
            else
            {
                try
                {
                    viewGroup=(BindableView<T>)builderMethod.invoke(null,new Object[]{parent.getContext()});
                }
                catch(Exception ex)
                {

                }
            }
        }
        viewGroup.setListEventListener(listEventListener);
        viewGroup.bind((T) getItem(position));
        return viewGroup;
    }

    public void setListEventListener(ListEventListener listEventListener) {
        this.listEventListener = listEventListener;
    }

    public void notifyAction(int actionId, T object, View view) {
        if (listEventListener != null) {
            listEventListener.onListEvent(actionId, object, view);
        }
    }
}
