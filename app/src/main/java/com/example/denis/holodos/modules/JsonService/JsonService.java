package com.example.denis.holodos.modules.JsonService;

import com.example.denis.holodos.modules.Group;
import com.example.denis.holodos.modules.User;
import com.example.denis.holodos.modules.receipts.Item;
import com.example.denis.holodos.modules.receipts.Receipt;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 17.01.2018.
 */

public class JsonService {
    public static User createUser(JSONObject JsonUser) {
        User user = new User();

        try {
            user.setLogin(JsonUser.getString("login"));
            user.setPassword(JsonUser.getString("password"));
            user.setId(JsonUser.getLong("id"));

            Group group;
            try {
                JSONObject JsonGroup = JsonUser.getJSONObject("group");
                group = createGroup(JsonGroup);
            } catch (JSONException e) {
                e.printStackTrace();
                group = null;
            }
            user.setGroup(group);

            return user;
        } catch (JSONException e) {
            e.printStackTrace();
            return user;
        }

    }

    public static Group createGroup(JSONObject JsonGroup) {
        Group group = new Group();
        try {
            group.setTitle(JsonGroup.getString("title"));
            group.setId(JsonGroup.getLong("id"));
            return group;
        } catch (JSONException e) {
            e.printStackTrace();
            return group;
        }
    }

    public static Receipt createReceipt(JSONObject JsonReceipt) {
        Receipt receipt = new Receipt();

        User owner;
        try {
            JSONObject JsonUser = JsonReceipt.getJSONObject("user");
            owner = createUser(JsonUser);
        } catch (JSONException e) {
            e.printStackTrace();
            owner = null;
        }
        receipt.setOwner(owner);

        Group group;
        try {
            JSONObject JsonGroup = JsonReceipt.getJSONObject("group");
            group = createGroup(JsonGroup);
        }  catch (JSONException e) {
            e.printStackTrace();
            group = null;
        }

        receipt.setGroup(group);
        try {
            receipt.setId(JsonReceipt.getLong("id"));
            receipt.setOperator(JsonReceipt.getString("operator"));
            receipt.setUser(JsonReceipt.getString("user"));
            receipt.setUserInn(JsonReceipt.getString("userInn"));
            receipt.setTotalSum(JsonReceipt.getDouble("totalSum"));
            receipt.setFiscalDriveNumber(JsonReceipt.getString("fiscalDriveNumber"));
            receipt.setKktRegId(JsonReceipt.getString("kktRegId"));
            receipt.setFiscalDocumentNumber(JsonReceipt.getString("fiscalDocumentNumber"));
            receipt.setCashTotalType(JsonReceipt.getDouble("cashTotalType"));
            receipt.setEcashTotalSum(JsonReceipt.getDouble("ecashTotalSum"));
            receipt.setRetailPlaceAddress(JsonReceipt.getString("retailPlaceAddress"));
            receipt.setFiscalSign(JsonReceipt.getString("fiscalSign"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        List<Item> items = new ArrayList<>();

        //receipt.setItems();

        return receipt;
    }
}
