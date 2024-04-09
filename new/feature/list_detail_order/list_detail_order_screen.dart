import 'package:datn/AppRoutes.dart';
import 'package:datn/feature/list_detail_order/list_detail_order_controller.dart';
import 'package:datn/screen/home_details/home_details.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';

class ListDetailOrderScreen extends StatefulWidget {
  const ListDetailOrderScreen({super.key});

  @override
  State<ListDetailOrderScreen> createState() => _ListDetailOrderScreenState();
}

class _ListDetailOrderScreenState extends State<ListDetailOrderScreen> {
  final orderController = Get.put(ListDetailOrderController());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        resizeToAvoidBottomInset: false,
        appBar: AppBar(
          title: const Text(
            "Chi tiết hóa đơn",
            style: TextStyle(
                color: Colors.black, fontSize: 23, fontWeight: FontWeight.bold),
          ),
          leading: IconButton(
            icon: const Icon(Icons.arrow_back),
            onPressed: () {
              Get.off(const HomeDetailView());
            },
          ),
        ),
        body: Padding(
          padding: const EdgeInsets.all(16.0),
          child: SingleChildScrollView(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Obx(()=>


                    ListView.builder(
                    shrinkWrap: true,
                      itemCount: orderController.listTableDetailOrders.length,
                      itemBuilder:(context, index) {

                            var orderData = orderController.listTableDetailOrders.value[index];

                            return buildBillOrder(
                                orderData[0].isEmpty? DateFormat('dd/MM/yyyy').format(DateTime.now()) : orderData[0],
                                orderData[1], index, onClick);
                        },),


                ),
              ],
            ),
          ),
        ));
  }

  void onClick(int index){
    // print(orderController.listDetailOrders.value[index - 1]);
    final dataOrder = orderController.listDetailOrders.value[index - 1];
    var dataMap = {
      'idBill': dataOrder.idBill,
      'nameRoom': Get.parameters['nameRoom'] ?? "",
      'customer': Get.parameters['customer'] ?? "",
      'phone': Get.parameters['phone'] ?? "",
      'idRoom': dataOrder.idRoom,
      'dateOfBill': dataOrder.dateOfBill,
      'moneyRoom': "${dataOrder.moneyRoom}",
      'numberElectricNew': "${dataOrder.numberElectricNew}",
      'numberElectricOld': "${dataOrder.numberElectricOld}",
      'numberWaterNew': "${dataOrder.numberWaterNew}",
      'numberWaterOld': "${dataOrder.numberWaterOld}",
      'totalService': "${dataOrder.totalService}",
      'totalMoneyWater': "${dataOrder.totalMoneyWater}",
      'totalMoneyElectric': "${dataOrder.totalMoneyElectric}",
      'totalMoneyToPay': "${dataOrder.totalMoneyToPay}",
    };
    Get.toNamed(AppRoutes.watchOrderRoute,parameters: dataMap);
  }

  Widget buildBillOrder(String col1, String col2, int index, void Function(int) onClick){
    return Row(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Expanded(flex: 1 ,child: Text(col1) ),
        Expanded(flex: 1 ,child: Text(col2) ),
        Expanded(flex: 1 ,child: buildWidgets(index,onClick)),
      ],
    );
  }
  Widget buildWidgets(int index, void Function(int) onClick){
    return index == 0 ? const Text("") :  InkWell(
      onTap: (){
        onClick(index);
      },
      child: const Text("Xem",style: TextStyle(
      color: Colors.blue
    ),),);
  }
}
