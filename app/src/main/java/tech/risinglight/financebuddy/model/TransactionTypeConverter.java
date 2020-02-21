package tech.risinglight.financebuddy.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class TransactionTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<SplitwiseTransaction> stringToTransactionList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<SplitwiseTransaction>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String transactionListToString(List<SplitwiseTransaction> someObjects) {
        return gson.toJson(someObjects);
    }
}