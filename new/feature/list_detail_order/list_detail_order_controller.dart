
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:datn/feature/create_bill/create_bill_model.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';

class ListDetailOrderController extends GetxController {
  final listDetailOrders = RxList<DetailBillModel>([]).obs;
  final listTableDetailOrders = <List<String>>[].obs;
  final orderStore = FirebaseFirestore.instance;

  @override
  void onInit() async{
    // TODO: implement onInit
    await getListDetailOrdersByIdRoom(Get.parameters["idRoom"] ?? "");
    super.onInit();
  }

  Future<void> getListDetailOrdersByIdRoom(String idRoom) async{
     final orderDataList = await orderStore.collection("DetailBills").get();
     listDetailOrders.value.clear();
     for(var orderDataList in orderDataList.docs) {
       DetailBillModel billModel = DetailBillModel.fromJson( orderDataList.data());
       if(billModel.idRoom == idRoom) listDetailOrders.value.add(billModel);

     }
     listTableDetailOrders.clear();
     if(listDetailOrders.value.isEmpty){
       listTableDetailOrders.add(["Ngày", "Tổng tiền"]);
     }else {
       listTableDetailOrders.add(["Ngày", "Tổng tiền"]);
       for(var dataTable in listDetailOrders.value){
         final dayOfBill = dataTable.dateOfBill.isEmpty ? DateFormat('dd/MM/yyyy').format(DateTime.now()): dataTable.dateOfBill;
         listTableDetailOrders.add([dayOfBill,"${dataTable.totalMoneyToPay}"]);
       }
     }
  }

}