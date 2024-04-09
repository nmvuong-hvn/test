import 'dart:ui';

import 'package:datn/common/CommonButton.dart';
import 'package:datn/common/CommonDataTable.dart';
import 'package:datn/common/CommonDropMenuItem.dart';
import 'package:datn/common/CommonPickerImageElecAndWater.dart';
import 'package:datn/common/CommonTexFieldNormal.dart';
import 'package:datn/common/CommonTextElectricAndWater.dart';
import 'package:datn/common/ComonPickerTimeAndEdt.dart';
import 'package:datn/feature/create_bill/create_bill_model.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:get/get.dart';
import 'package:get/get_state_manager/get_state_manager.dart';
import 'package:get/get_state_manager/src/rx_flutter/rx_obx_widget.dart';
import 'package:get/state_manager.dart';
import 'package:intl/intl.dart';
import 'create_bill_controller.dart';

class CreateBillingScreen extends StatefulWidget {
  const CreateBillingScreen({super.key});

  @override
  State<CreateBillingScreen> createState() => _CreateBillingScreenState();
}

class _CreateBillingScreenState extends State<CreateBillingScreen> {
  final controller = Get.put(CreateBillController());
  final edtPriceRoom = TextEditingController();
  final edtNotes = TextEditingController();

  final edtNumberElectricOld = TextEditingController();
  final edtNumberWaterOld = TextEditingController();
  final edtNumberWaterNew = TextEditingController();
  final edtNumberElectricNew = TextEditingController();

  String dateTime =  DateFormat('dd/MM/yyyy').format(DateTime.now());
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    edtNumberElectricNew.addListener(() {
        if(edtNumberElectricNew.text.isNotEmpty && edtNumberElectricOld.text.isNotEmpty ) {
          final dataElectric = int.parse(edtNumberElectricNew.text) -
              int.parse(edtNumberElectricOld.text);
          controller.setNumberElectric(dataElectric < 0 ? 0 : dataElectric);
        }
    });
    edtNumberWaterNew.addListener(() {
        if(edtNumberWaterNew.text.isNotEmpty && edtNumberWaterOld.text.isNotEmpty) {
          final dataWater = int.parse(edtNumberWaterNew.text) -
              int.parse(edtNumberWaterOld.text);
          controller.setNumberWater(dataWater < 0 ? 0 : dataWater);
        }
    });

    edtPriceRoom.addListener(() {
      try {
        if(edtPriceRoom.text.isNotEmpty) controller.setNumberPriceRoom(int.tryParse(edtPriceRoom.text.toString()) ?? 0);
      }on Exception catch (_, e){

      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        title: const Text( "Tạo hóa đơn",
          style: TextStyle(
              color: Colors.black, fontSize: 23, fontWeight: FontWeight.bold),
        ),
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(1.0),
          child: Card(
            elevation: 10,
            shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Container(
                  width: double.infinity,
                  color: Colors.redAccent,
                  child: const Padding(
                    padding: EdgeInsets.all(16.0),
                    child: Text("a", style: TextStyle(fontWeight: FontWeight.bold)),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 16),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const SizedBox(height: 5),
                      const Text("Ngày",
                          style: TextStyle(fontWeight: FontWeight.bold)),
                      const SizedBox(height: 5),
                      CommonPickerTimeAndEdt(
                        dateTime: dateTime,
                        icon: const Icon(Icons.calendar_month),
                        onClick: () async{

                          DateTime? pickedDate = await showDatePicker(
                              context: context,
                              initialDate: DateTime.now(), //get today's date
                              firstDate:DateTime.now(), //DateTime.now() - not to allow to choose before today.
                              lastDate: DateTime(2101)
                          );
                          if(pickedDate != null ){
                            String formattedDate = DateFormat('dd/MM/yyyy').format(pickedDate);
                            setState(() {
                              dateTime = formattedDate;
                            });
                          }else{
                            print("Date is not selected");
                          }
                        },
                      ),
                      const SizedBox(height: 5),
                      const Text("Thông tin điện nước cũ",
                          style: TextStyle(fontWeight: FontWeight.bold)),
                      const SizedBox(
                        height: 8,
                      ),
                        buildCommonBill("Số điện cũ", "Số nước cũ", "Hình điện cũ", "Hình nước cũ",
                        edtNumberElectricOld, edtNumberWaterOld, controller, false
                          ,(p0) {
                              controller.getInfoOldBillByDate(p0);
                            }),
                      const SizedBox(
                        height: 8,
                      ),
                      const Text("Thông tin điện nước mới",
                          style: TextStyle(fontWeight: FontWeight.bold)),
                      const SizedBox(
                        height: 8,
                      ),
                    buildCommonBill("Số điện mới",  "Số nước mới",
                            "Hình điện mới", "Hình nước mới", edtNumberElectricNew, edtNumberWaterNew,controller,true,(p0) {
                                controller.getInfoNewBillByDate(p0);
                            }),

                      const SizedBox(
                        height: 5,
                      ),
                      const Text("Tiền dịch vụ thêm",
                          style: TextStyle(fontWeight: FontWeight.bold)),
                      const SizedBox(
                        height: 10,
                      ),
                      CommonTextFieldNormal(
                          controller: edtPriceRoom,
                          title: "Tiền phòng",
                          hintText: "",
                          keyboardType: TextInputType.number),
                      const SizedBox(height: 8),
                      CommonTextFieldNormal(
                          controller: edtNotes,
                          title: "Ghi chú",
                          hintText: "Ví dụ",
                          keyboardType: TextInputType.text),
                      const SizedBox(height: 8),
                      Obx( ()=>
                          ListView.builder(
                            shrinkWrap: true,
                            itemCount: controller.rowList.length,
                            itemBuilder: (context, index) {
                              final dataColumn = controller.rowList.value[index];
                            return CommonDataTable(col1: dataColumn[0],
                                col2: dataColumn[1], col3:
                                dataColumn[2], col4:
                                dataColumn[3]);
                          },)

                      ),

                      Padding(
                        padding: const EdgeInsets.all(16.0),
                        child: Center(
                          child: CommonButton(
                              nameButton: "Duyệt hóa đơn",
                              onClick: () async{
                                final totalMoneyWater = controller.numberWater.value * controller.settingModel.priceWater;
                                final totalMoneyElectric =  controller.numberElectric.value * controller.settingModel.priceElectricity;
                                final totalService = controller.getTotalService();
                                DetailBillModel detailBillModel =
                                DetailBillModel(Get.parameters['idRoom'] ?? "",
                                    "${DateTime.now().microsecondsSinceEpoch}",
                                    dateTime,
                                    int.parse(edtPriceRoom.text),
                                    int.parse(edtNumberElectricNew.text),
                                    int.parse(edtNumberElectricOld.text),
                                    int.parse(edtNumberWaterNew.text),
                                    int.parse(edtNumberWaterOld.text),
                                    totalService,
                                    totalMoneyWater,
                                    totalMoneyElectric,
                                    controller.totalMoneyNeedToPay.value,
                                    edtNotes.text
                                );
                                controller.createBill(detailBillModel);
                                // print("Duyệt hóa đơn");
                              }),
                        ),
                      )
                    ],
                  ),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget buildCommonBill(String titleElec, String titleWater, String titleImageElec, String titleImageWater,
      TextEditingController editingController, TextEditingController controller, CreateBillController billController,
  bool isNew,
  void Function(String) onSelected,

      ) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const Text(
          "Thời gian ghi nhận",
          style: TextStyle(fontWeight: FontWeight.bold),
        ),
        const SizedBox(height: 5),
        Obx(() =>
            buildTimeConfirmation(
                billController.infoConfirmedList.value, onSelected)),
        const SizedBox(height: 8),
        Row(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const SizedBox(height: 8),
            Obx(() =>
                CommonTextElectricAndWater(title: titleElec, label:
                isNew ?
                ("${billController.newBillRoomList.value
                    .toList()
                    .isEmpty ? 0
                    : billController.newBillRoomList.value.toList()[0]
                    .numberElectric }") :
                ("${billController.oldBillRoomList.value
                    .toList()
                    .isEmpty ? 0
                    : billController.oldBillRoomList.value.toList()[0]
                    .numberElectric }")

                  , editingController: editingController,)),
            const SizedBox(width: 10,),
            Obx( ()=> CommonTextElectricAndWater(title: titleWater, label:
              isNew ? ("${billController.newBillRoomList.value
                  .toList()
                  .isEmpty ? 0
                  : billController.newBillRoomList.value.toList()[0]
                  .numberWater }") :
              ("${billController.oldBillRoomList.value
                  .toList()
                  .isEmpty ? 0
                  : billController.oldBillRoomList.value.toList()[0]
                  .numberWater }")
                , editingController: controller,),
            )
          ],
        ),
        const SizedBox(height: 8),
        Row(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              SizedBox(
                width: 150,
                child: Obx( ()=> CommonPickerImageElecAndWater(
                    title: titleImageElec, imageUrl:
                  isNew ? (
                      billController.newBillRoomList.value.isEmpty ? "" :
                      billController.newBillRoomList.value.toList()[0]
                          .urlElectricImageNew) :
                  (
                      billController.oldBillRoomList.value.isEmpty ? "" :
                      billController.oldBillRoomList.value.toList()[0]
                          .urlElectricImageOld)
                    , selected: () {},
                  ),
                ),
              ),
              const SizedBox(width: 30,),
              SizedBox(
                  width: 150,
                  child: Obx( ()=> CommonPickerImageElecAndWater(
                      title: titleImageWater, imageUrl:
                    isNew ? (
                        billController.newBillRoomList.value.isEmpty ? "" :
                        billController.newBillRoomList.value.toList()[0]
                            .urlElectricImageNew) :
                    (
                        billController.oldBillRoomList.value.isEmpty ? "" :
                        billController.oldBillRoomList.value.toList()[0]
                            .urlElectricImageOld)
                      ,

                      selected: () {},
                    ),
                  ))
            ])
      ],
    );
  }

  Widget buildTimeConfirmation(List<String> dataTime, void Function(String) onSelected){
    return dataTime.isEmpty ? Container() :  CommonDropMenuItem(
        items: controller.infoConfirmedList.value,
        initialValue: 0,
        onSelected: onSelected,
        offsetWidth: 90);
  }
}
