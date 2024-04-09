import 'dart:ffi';

import 'package:datn/common/CommonButton.dart';
import 'package:datn/common/CommonDropMenuItem.dart';
import 'package:datn/common/CommonPickerImageElecAndWater.dart';
import 'package:datn/common/CommonTexFieldNormal.dart';
import 'package:datn/common/ComonPickerTimeAndEdt.dart';
import 'package:datn/feature/create_electric_water/create_electric_water_controller.dart';
import 'package:datn/feature/create_electric_water/water_electric_bill_model.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:get/get.dart';
import 'package:get/get_state_manager/get_state_manager.dart';
import 'package:get/get_state_manager/src/rx_flutter/rx_obx_widget.dart';
import 'package:get/state_manager.dart';
import 'package:intl/intl.dart';

class CreateElectricAndWaterScreen extends StatefulWidget {
  const CreateElectricAndWaterScreen({super.key});

  @override
  State<CreateElectricAndWaterScreen> createState() =>
      _CreateElectricAndWaterScreenState();
}

class _CreateElectricAndWaterScreenState
    extends State<CreateElectricAndWaterScreen> {
  final edtNumberElectricity = TextEditingController();
  final edtNumberWater = TextEditingController();
  String nameRoom = "";
  final controller = Get.put(CreateElectricAndWaterController());
  String dateTime = DateFormat('dd/MM/yyyy').format(DateTime.now());
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    Future.delayed(const Duration(seconds: 2));
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        resizeToAvoidBottomInset: false,
        appBar: AppBar(
          title: const Text(
            "Ghi điện nước",
            style: TextStyle(
                color: Colors.black, fontSize: 23, fontWeight: FontWeight.bold),
          ),
        ),
        body: SingleChildScrollView(
          physics: const ScrollPhysics(),
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: 16.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    const Text(
                      "Chọn phòng",
                      style: TextStyle(fontSize: 13),
                    ),
                    Obx(
                      () =>
                          CommonDropMenuItem(
                              items: controller.roomsList.value.isNotEmpty ?
                              controller.roomsList.value.map((element) => element.nameRoom).toList():
                              ["None"],
                              initialValue:0,
                              offsetWidth: 143, onSelected: (e){
                                nameRoom = e;
                            } )
                    )
                  ],
                ),
                const SizedBox(height: 10),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                     const SizedBox(width: 100, child: Text("Ngày ghi")),
                    SizedBox(
                      width: 250,
                      child: CommonPickerTimeAndEdt(
                        dateTime: dateTime,
                        icon: const Icon(Icons.calendar_month),
                        onClick: () async {
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
                    )
                  ],
                ),
                const SizedBox(height: 10),
                buildInfoElecAndWater(),
                Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Center(
                    child: CommonButton(
                        nameButton: "Ghi số liệu",
                        onClick: () async {

                          print("nameRoom = $nameRoom");
                          final home = controller.roomsList.value
                              .toList()
                              .firstWhere(
                                  (element) => element.nameRoom == nameRoom);
                          print("$home");
                          print("numberElect = " + edtNumberElectricity.text + " water = " + edtNumberWater.text);
                          WaterElectricBillModel billModel =
                              WaterElectricBillModel(
                                  "${DateTime.now().microsecondsSinceEpoch}",
                                  DateTime.now().microsecondsSinceEpoch,
                                  home.idRoom,
                                  dateTime,
                                  int.parse(edtNumberElectricity.text),
                                  int.parse(edtNumberWater.text),
                                  controller.imageElectricNewUrl.value,
                                  controller.imageElectricOldUrl.value);
                          await controller.createElectricAndBill(
                              home.idRoom, billModel);
                        }),
                  ),
                ),
                Obx(
                  () => ListView.builder(
                    scrollDirection: Axis.vertical,
                    shrinkWrap: true,
                    itemCount: controller.billsList.value.toList().length,
                    itemBuilder: (context, index) {
                      return buildInfoElectricAndWater(controller.billsList.value.toList()[index]);
                    },
                  ),
                ),
              ],
            ),
          ),
        ));
  }

  Widget buildInfoElecAndWater() {
    return Card(
      elevation: 10,
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              const Text("Phòng"),
              const SizedBox(
                height: 5,
              ),
              const Text("Người nhận"),
              const SizedBox(
                height: 5,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  SizedBox(
                    width: 150,
                    child: CommonTextFieldNormal(
                        controller: edtNumberElectricity,
                        title: "Số điện",
                        hintText: "Nhập số điện",
                        keyboardType: TextInputType.number),
                  ),
                  SizedBox(
                    width: 150,
                    child: CommonTextFieldNormal(
                        controller: edtNumberWater,
                        title: "Số nước",
                        hintText: "Nhập số nước",
                        keyboardType: TextInputType.number),
                  )
                ],
              ),
              const SizedBox(
                height: 5,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  SizedBox(
                      width: 150,
                      child: buildImageClockAndWater("Hình điện cũ", false)),
                  SizedBox(
                    width: 150,
                      child: buildImageClockAndWater("Hình điện mới", true)),
                ],
              )
            ]),
      ),
    );
  }

  Widget buildImageClockAndWater(String title, bool isNew) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Obx(
          () => CommonPickerImageElecAndWater(
              title: title,
              imageUrl: isNew
                  ? controller.imageElectricNewUrl.value
                  : controller.imageElectricOldUrl.value,
              selected: () {
                if (isNew) {
                  controller.getImageNew();
                } else {
                  controller.getImageOld();
                }
              }),
        )
      ],
    );
  }
}

Widget buildInfoElectricAndWater(WaterElectricBillModel billModel) {

  return Card(
    elevation: 10,
    color: Colors.white,
    child: Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 16),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          buildInfoHeaderAndAction("Phòng"),
          const SizedBox(height: 15),
          const SizedBox(height: 5),
          buildItemInfoRoom("Ngày ghi", "26/03/2024"),
          const SizedBox(height: 5),
          const Divider(height: 1.0),
          const SizedBox(height: 5),
          buildItemInfoRoom("Người thuê", ""),
          const SizedBox(height: 5),
          const Divider(height: 1.0),
          const SizedBox(height: 5),
          buildItemInfoRoom("Số điện", "${billModel.numberElectric}"),
          const SizedBox(height: 5),
          const Divider(height: 1.0),
          const SizedBox(height: 5),
          buildItemInfoRoom("Số nước", "${billModel.numberWater}"),
          const SizedBox(height: 5),
          const Divider(height: 1.0),
          const SizedBox(height: 5),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              SizedBox(
                  width: 150,
                  child: SizedBox(
                    child: Image.network(billModel
                            .urlElectricImageNew.isNotEmpty
                        ? billModel.urlElectricImageNew
                        : "https://pixsector.com/cache/517d8be6/av5c8336583e291842624.png"),
                  )),
              //  Expanded(
              //   flex: 1, child: Container(),
              // ),
              SizedBox(
                  width: 150,
                  child: SizedBox(
                    child: Image.network(billModel
                            .urlElectricImageOld.isNotEmpty
                        ? billModel.urlElectricImageOld
                        : "https://pixsector.com/cache/517d8be6/av5c8336583e291842624.png"),
                  )),
            ],
          )
        ],
      ),
    ),
  );
}

Widget buildInfoHeaderAndAction(String s) {
  return Row(
    mainAxisAlignment: MainAxisAlignment.spaceBetween,
    crossAxisAlignment: CrossAxisAlignment.start,
    children: [
      Text(s),
      const Row(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Icon(Icons.delete_forever_rounded),
          SizedBox(width: 10),
          Icon(Icons.edit)
        ],
      )
    ],
  );
}

Widget buildItemInfoRoom(String title, String data) {
  return Row(
    mainAxisAlignment: MainAxisAlignment.spaceBetween,
    crossAxisAlignment: CrossAxisAlignment.start,
    children: [Text(title), Text(data)],
  );
}
