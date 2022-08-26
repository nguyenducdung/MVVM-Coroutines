package com.dungnd.mvvm.data

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor(
    private val context: Context
) {

    suspend fun getPhone(): List<String> {
        val phoneList = ArrayList<String>()
        val uri = Uri.parse("content://contacts/people")
        //Tạo 1 cái cursor (1 cái dùng để query lấy dữ liệu 1 cách thủ công)
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        if ((cursor?.count ?: 0) > 0) {
            //Bắt đầu
            cursor?.moveToFirst()

            while (cursor?.isAfterLast != true) {
                val id = cursor?.getColumnIndex(
                    ContactsContract.Contacts._ID)?.let { cursor.getString(it) }

                //Lấy số điện thoại
                val cursorPhone = context.contentResolver?.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null)

                //Bắt đầu
                cursorPhone?.moveToFirst()

                while (cursorPhone?.isAfterLast != true) {
                    val phone = cursorPhone?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)?.let { cursorPhone.getString(it) }

                    phoneList.add(phone ?:"")

                    cursorPhone?.moveToNext()
                }

                //Kết thúc lấy phone
                cursorPhone.close()

                cursor?.moveToNext()
            }

            //Kết thúc
            cursor.close()
        }

        return phoneList
    }
}