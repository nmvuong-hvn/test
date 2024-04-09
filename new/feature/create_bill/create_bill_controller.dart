
import 'dart:ffi';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:datn/feature/create_bill/create_bill_model.dart';
import 'package:get/get.dart';
import 'package:get_storage/get_storage.dart';
import 'package:intl/intl.dart';

import '../create_electric_water/water_electric_bill_model.dart';
import '../setting_house/setting_house_model.dart';

class CreateBillController extends GetxController {

   final db = FirebaseFirestore.instance;
   var billList = RxList<WaterElectricBillModel>().obs;
   var infoConfirmedList = <String>[].obs;
   var billIdRoomList = RxList<WaterElectricBillModel>();
   var oldBillRoomList = RxList<WaterElectricBillModel>().obs;
   var newBillRoomList = RxList<WaterElectricBillModel>().obs;
   var numberElectric = 0.obs;
   var numberWater = 0.obs;
   var numberPriceRoom = 0.obs;
   var totalService = 0.obs;
   var storage = GetStorage();
   var rowList = <List<String>>[].obs;
   late SettingHouseModel settingModel ;
   var totalMoneyNeedToPay = 0.obs;


   void setNumberElectric(int data){
     if(totalMoneyNeedToPay.value > 0 ) totalMoneyNeedToPay.value -= (numberElectric.value * settingModel.priceElectricity) ;
     totalMoneyNeedToPay.value += (data * settingModel.priceElectricity) ;
     numberElectric.value = data;
     final listHeader = ["","Số","Giá","Tiền"];
     final listWater = ["Tiền nước","${numberWater.value}","${settingModel.priceWater}","${numberWater.value * settingModel.priceWater}"];
     final listElect = ["Tiền điện","${numberElectric.value}","${settingModel.priceElectricity}","${numberElectric.value * settingModel.priceElectricity}"];
     final listRoom = ["Tiền phòng","","${numberPriceRoom.value}","${numberPriceRoom.value}"];
     final listService = ["Dịch vụ","2","${getTotalService()}","${getTotalService()}"];
     final listTotal = ["Tổng","","","${totalMoneyNeedToPay.value}"];
     rowList.clear();
     rowList.add(listHeader);
     rowList.add(listWater);
     rowList.add(listElect);
     rowList.add(listRoom);
     rowList.add(listService);
     rowList.add(listTotal);
   }

   void setNumberWater(int data){
     if(totalMoneyNeedToPay.value > 0 ) totalMoneyNeedToPay.value -= (numberWater.value * settingModel.priceWater) ;
     totalMoneyNeedToPay.value += (data * settingModel.priceWater);
     numberWater.value = data;
     final listHeader = ["","Số","Giá","Tiền"];

     final listWater = ["Tiền nước","${numberWater.value}","${settingModel.priceWater}","${numberWater.value * settingModel.priceWater}"];
     final listElect = ["Tiền điện","${numberElectric.value}","${settingModel.priceElectricity}","${numberElectric.value * settingModel.priceElectricity}"];
     final listRoom = ["Tiền phòng","","${numberPriceRoom.value}","${numberPriceRoom.value}"];
     final listService = ["Dịch vụ","2","${getTotalService()}","${getTotalService()}"];
     final listTotal = ["Tổng","","","${totalMoneyNeedToPay.value}"];
     rowList.clear();
     rowList.add(listHeader);

     rowList.add(listWater);
     rowList.add(listElect);
     rowList.add(listRoom);
     rowList.add(listService);
     rowList.add(listTotal);
   }

   void setNumberPriceRoom(int data){
     print("daataMoney = $data");
     if(totalMoneyNeedToPay.value > 0 ) totalMoneyNeedToPay.value -= numberPriceRoom.value ;
     totalMoneyNeedToPay.value += data ;
     numberPriceRoom.value = data;
     final listHeader = ["","Số","Giá","Tiền"];

     final listWater = ["Tiền nước","${numberWater.value}","${settingModel.priceWater}","${numberWater.value * settingModel.priceWater}"];
     final listElect = ["Tiền điện","${numberElectric.value}","${settingModel.priceElectricity}","${numberElectric.value * settingModel.priceElectricity}"];
     final listRoom = ["Tiền phòng","","${numberPriceRoom.value}","${numberPriceRoom.value}"];
     final listService = ["Dịch vụ","2","${getTotalService()}","${getTotalService()}"];
     print("value = ${totalMoneyNeedToPay.value}");
     final listTotal = ["Tổng","","","${totalMoneyNeedToPay.value}"];
     rowList.clear();
     rowList.add(listHeader);

     rowList.add(listWater);
     rowList.add(listElect);
     rowList.add(listRoom);
     rowList.add(listService);
     rowList.add(listTotal);
     print(listTotal);
   }
   void setTotalService(int data){
     totalService.value = data;
     final listHeader = ["","Số","Giá","Tiền"];

     final listWater = ["Tiền nước","${numberWater.value}","${settingModel.priceWater}","${numberWater.value * settingModel.priceWater}"];
     final listElect = ["Tiền điện","${numberElectric.value}","${settingModel.priceElectricity}","${numberElectric.value * settingModel.priceElectricity}"];
     final listRoom = ["Tiền phòng","","${numberPriceRoom.value}","${numberPriceRoom.value}"];
     final listService = ["Dịch vụ","2","${getTotalService()}","${getTotalService()}"];
     final listTotal = ["Tổng","","","0"];
     rowList.clear();
     rowList.add(listHeader);

     rowList.add(listWater);
     rowList.add(listElect);
     rowList.add(listRoom);
     rowList.add(listService);
     rowList.add(listTotal);
   }
   @override
  void onInit() async {
    // TODO: implement onInit
     getData();
     await getAllBill();
     await getInfoBills();
      super.onInit();

  }
   SettingHouseModel getData() {
     final dayOfMonth = storage.read("dayOfMonth") ?? 0;
     final priceElectricity = storage.read("priceElectricity") ?? 0;
     final priceWater = storage.read("priceWater") ?? 0;
     final priceRoom = storage.read("priceRoom") ?? 0;
     final priceInternet = storage.read("priceInternet") ?? 0;
     final priceSecurity = storage.read("priceSecurity") ?? 0;
     settingModel = SettingHouseModel(dayOfMonth, priceElectricity, priceWater, priceRoom, priceInternet, priceSecurity);
     return settingModel;
   }
   int getTotalService()  {
     final setting = getData();
     totalService.value = setting.priceInternet + setting.priceSecurity;
     return setting.priceInternet + setting.priceSecurity;
  }

  int getTotalElectric(int numberElectric)  {

    return settingModel.priceElectricity * numberElectric;
  }

   int getTotalWater(int numberWater)  {
    return settingModel.priceWater * numberWater;
  }

  void createBill(DetailBillModel detailBillModel) async {
     await db.collection("DetailBills").doc("${detailBillModel.idRoom}_${DateTime.now().microsecondsSinceEpoch}").set(detailBillModel.toObjectMap());
     Get.snackbar("Thành công", "Duyệt hóa đơn thành công");
   }

   Future<void> getAllBill() async{
      var billsList = await db.collection("Bills").get();
      billList.value.clear();
      for(var bill in billsList.docs){
         billList.value.add(WaterElectricBillModel.fromJson(bill.data()));
      }
   }

   Future<void> getInfoBills() async{
     print("getInfoBills");
     infoConfirmedList.value.clear();
     infoConfirmedList.value.addAll(billList.value.asMap().entries.map((e) => "${e.key}. Thời gian ghi nhận ${e.value.timeStamp}").toList());

     final dataModel = billList.value.where((element) => infoConfirmedList.value[0].contains(element.timeStamp.toString())).first ;
     print(billList.value.toList());
     newBillRoomList.value.clear();
     oldBillRoomList.value.clear();

     newBillRoomList.value.add(dataModel);
     oldBillRoomList.value.add(dataModel);

     totalMoneyNeedToPay.value += settingModel.priceInternet + settingModel.priceSecurity;
     numberElectric.value = (newBillRoomList.value[0].numberElectric - oldBillRoomList.value[0].numberElectric) < 0 ? 0 :
     (newBillRoomList.value[0].numberElectric - oldBillRoomList.value[0].numberElectric);
     numberWater.value    = (newBillRoomList.value[0].numberWater - oldBillRoomList.value[0].numberWater) < 0 ? 0 :
     (newBillRoomList.value[0].numberWater - oldBillRoomList.value[0].numberWater);

     totalMoneyNeedToPay.value += (numberWater.value) * settingModel.priceWater;
     totalMoneyNeedToPay.value += (numberElectric.value) * settingModel.priceElectricity;

     final listHeader = ["","Số","Giá","Tiền"];
     final listWater = ["Tiền nước","${numberWater.value}","${settingModel.priceWater}","${numberWater.value * settingModel.priceWater}"];
     final listElect = ["Tiền điện","${numberElectric.value}","${settingModel.priceElectricity}","${numberElectric.value * settingModel.priceElectricity}"];
     final listRoom = ["Tiền phòng","","${numberPriceRoom.value}","${numberPriceRoom.value}"];
     final listService = ["Dịch vụ","2","${getTotalService()}","${getTotalService()}"];
     final listTotal = ["Tổng","","","${totalMoneyNeedToPay.value}"];
     rowList.clear();
     rowList.add(listHeader);

     rowList.add(listWater);
     rowList.add(listElect);
     rowList.add(listRoom);
     rowList.add(listService);
     rowList.add(listTotal);
     print(dataModel);
     infoConfirmedList.refresh();
   }
   void getBillByIdRoom(String idRoom) async{
      var billRoom = await db.collection("Bills").doc(idRoom).get();
      if(billRoom.data() != null) {
        billIdRoomList.value.add(WaterElectricBillModel.fromJson(billRoom.data()!));
      }
   }
   void getInfoOldBillByDate(String value){
     oldBillRoomList.value.clear();
    final dataModel = billList.value.where((element) => value.contains(element.timeStamp.toString())).first ;
    oldBillRoomList.value.add(dataModel);
     final dataElectric = (newBillRoomList.value[0].numberElectric - oldBillRoomList.value[0].numberElectric);
     final dateWater = newBillRoomList.value[0].numberWater - oldBillRoomList.value[0].numberWater;

     if(totalMoneyNeedToPay.value > 0 ) totalMoneyNeedToPay.value -= (numberWater.value) * settingModel.priceWater;
     if(totalMoneyNeedToPay.value > 0 ) totalMoneyNeedToPay.value -= (numberElectric.value) * settingModel.priceElectricity;

     numberElectric.value = dataElectric < 0 ? 0 : dataElectric ;
     numberWater.value    = dateWater < 0 ? 0 : dateWater ;

     totalMoneyNeedToPay.value += (numberWater.value) * settingModel.priceWater;
     totalMoneyNeedToPay.value += (numberElectric.value) * settingModel.priceElectricity;

     final listHeader = ["","Số","Giá","Tiền"];

     final listWater = ["Tiền nước","${numberWater.value}","${settingModel.priceWater}","${numberWater.value * settingModel.priceWater}"];
     final listElect = ["Tiền điện","${numberElectric.value}","${settingModel.priceElectricity}","${numberElectric.value * settingModel.priceElectricity}"];
     final listRoom = ["Tiền phòng","","${numberPriceRoom.value}","${numberPriceRoom.value}"];
     final listService = ["Dịch vụ","2","${getTotalService()}","${getTotalService()}"];
     final listTotal = ["Tổng","","","${totalMoneyNeedToPay.value}"];
     rowList.clear();
     rowList.add(listHeader);

     rowList.add(listWater);
     rowList.add(listElect);
     rowList.add(listRoom);
     rowList.add(listService);
     rowList.add(listTotal);

   }
   void getInfoNewBillByDate(String value){
     newBillRoomList.value.clear();
     final dataModel = billList.value.where((element) => value.contains(element.timeStamp.toString())).first ;
     newBillRoomList.value.add(dataModel);
     final dataElectric = (newBillRoomList.value[0].numberElectric - oldBillRoomList.value[0].numberElectric);
     final dateWater = newBillRoomList.value[0].numberWater - oldBillRoomList.value[0].numberWater;
     if(totalMoneyNeedToPay.value > 0 ) totalMoneyNeedToPay.value -= (numberWater.value) * settingModel.priceWater;
     if(totalMoneyNeedToPay.value > 0 ) totalMoneyNeedToPay.value -= (numberElectric.value) * settingModel.priceElectricity;


     numberElectric.value = dataElectric < 0 ? 0 : dataElectric ;
     numberWater.value    = dateWater < 0 ? 0 : dateWater ;

     totalMoneyNeedToPay.value += (numberWater.value) * settingModel.priceWater;
     totalMoneyNeedToPay.value += (numberElectric.value) * settingModel.priceElectricity;

     print("dataElectric = $dataElectric - dataWater = $dateWater" );
     final listHeader = ["","Số","Giá","Tiền"];

     final listWater = ["Tiền nước","${numberWater.value}","${settingModel.priceWater}","${numberWater.value * settingModel.priceWater}"];
     final listElect = ["Tiền điện","${numberElectric.value}","${settingModel.priceElectricity}","${numberElectric.value * settingModel.priceElectricity}"];
     final listRoom = ["Tiền phòng","","${numberPriceRoom.value}","${numberPriceRoom.value}"];
     final listService = ["Dịch vụ","2","${getTotalService()}","${getTotalService()}"];
     final listTotal = ["Tổng","","","${totalMoneyNeedToPay.value}"];
     rowList.clear();
     rowList.add(listHeader);

     rowList.add(listWater);
     rowList.add(listElect);
     rowList.add(listRoom);
     rowList.add(listService);
     rowList.add(listTotal);
     print(rowList.length);
     print(rowList[0]);
     print(rowList[1]);
     print(rowList[2]);
     print(rowList[3]);
   }
}