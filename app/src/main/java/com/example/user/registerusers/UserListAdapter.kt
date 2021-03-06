package com.example.user.registerusers

import android.annotation.TargetApi
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.user.registerusers.DB.UserData

data class ViewHolder(val pic:ImageView,
                      val name:TextView,
                      val tel:TextView,
                      val email:TextView,
                      val del_item:ImageButton)


class UserListAdapter (context: Context, cursor: Cursor) : CursorAdapter(context,cursor, FLAG_REGISTER_CONTENT_OBSERVER){

    val mCtx = context

    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val mainView = inflater.inflate(R.layout.layout_user_list, parent,false)

        var holder:ViewHolder = ViewHolder(
                mainView.findViewById(R.id.profile),
                mainView.findViewById(R.id.name),
                mainView.findViewById(R.id.tel_num),
                mainView.findViewById(R.id.email),
                mainView.findViewById(R.id.del_item))
        mainView.tag = holder
        return mainView
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun bindView(view: View, context: Context, cursor: Cursor) {
        val holder = view.tag as ViewHolder

        holder.name.text = cursor.getString(UserData.Name.index)
        holder.tel.text = cursor.getString(UserData.TelNum.index)
        holder.email.text = cursor.getString(UserData.Email.index)

        val picture:Drawable =
                getPicture(cursor.getString(UserData.PicPath.index))?:context.getDrawable(android.R.drawable.ic_menu_gallery)

        picture.let {
            holder.pic.background = picture
        }

        //save cursor id
        holder.del_item.tag = cursor.getLong(0)
    }

    private fun getPicture(path:String): Drawable? {
        val img_id = path.toLong()
        if(img_id == 0L) return null

        var bitmap:Bitmap?
                = MediaStore.Images.Thumbnails.getThumbnail(mCtx.contentResolver,img_id,
                MediaStore.Images.Thumbnails.MICRO_KIND,null)

        bitmap?:return null
        return BitmapDrawable(mCtx.resources, bitmap)
    }

}