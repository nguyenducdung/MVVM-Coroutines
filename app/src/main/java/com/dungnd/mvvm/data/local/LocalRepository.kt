package com.dungnd.mvvm.data.local

import com.dungnd.mvvm.data.local.dao.FavouriteQuoteDao
import com.dungnd.mvvm.data.local.dao.ItemDao
import com.dungnd.mvvm.data.local.dao.UserDao
import com.dungnd.mvvm.data.local.model.FavouriteQuote
import com.dungnd.mvvm.data.local.model.Item
import com.dungnd.mvvm.data.local.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val favouriteQuoteDao: FavouriteQuoteDao,
    private val itemDao: ItemDao,
    private val userDao: UserDao
) {

    suspend fun saveUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun getUser(): User {
        return userDao.getAllUser().get(0)
    }

    suspend fun addFavouriteQuote(favouriteQuote: FavouriteQuote) {
        favouriteQuoteDao.addQuote(favouriteQuote)
    }

    suspend fun deleteFavouriteQuote(favouriteQuote: FavouriteQuote) {
        favouriteQuoteDao.removeFromFavourite(favouriteQuote)
    }

    suspend fun getAllFavouriteQuotes(): List<FavouriteQuote> {
        return favouriteQuoteDao.getAllQuotes()
    }

    suspend fun addItem(item: Item) = itemDao.addItem(item)

    suspend fun getItemWithId(id: Int) = itemDao.getItemWithId(id)

    suspend fun getAllItems() = itemDao.getAllItems()

    suspend fun getFinishedItemCountByType(type: String) =
        itemDao.getFinishedItemCountByType(type)

    suspend fun getWatchedEpisodesSum() = itemDao.getWatchedEpisodesSum()
}