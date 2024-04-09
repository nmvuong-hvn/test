import 'dart:ui';
import 'package:datn/common/CommonButton.dart';
import 'package:datn/common/CommonDropMenuItem.dart';
import 'package:datn/common/CommonTexFieldNormal.dart';
import 'package:datn/feature/setting_house/setting_house_controller.dart';
import 'package:datn/feature/setting_house/setting_house_model.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class SettingHouseScreen extends StatefulWidget {
  const SettingHouseScreen({super.key});

  @override
  State<SettingHouseScreen> createState() => _SettingHouseScreenState();
}

class _SettingHouseScreenState extends State<SettingHouseScreen> {
  final controller = Get.put(SettingHouseController());
  final edtPriceElectricity = TextEditingController();
  final edtPriceWater = TextEditingController();
  final edtPriceInternet = TextEditingController();
  final edtPriceSecurity = TextEditingController();
  final edtPriceRoom = TextEditingController();
  int index = 0;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    SettingHouseModel settingHouseModel = controller.getData();
    index = settingHouseModel.dayOfMonth;
    edtPriceSecurity.value =
        TextEditingValue(text: "${settingHouseModel.priceSecurity}");
    edtPriceElectricity.value =
        TextEditingValue(text: "${settingHouseModel.priceElectricity}");
    edtPriceWater.value =
        TextEditingValue(text: "${settingHouseModel.priceWater}");
    edtPriceInternet.value =
        TextEditingValue(text: "${settingHouseModel.priceInternet}");
    edtPriceRoom.value =
        TextEditingValue(text: "${settingHouseModel.priceRoom}");
  }
  void onPositionSelected(int id){
    index = id ;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        resizeToAvoidBottomInset: false,
        appBar: AppBar(
          title: const Text(
            "Cài đặt nhà trọ",
            style: TextStyle(
                color: Colors.black, fontSize: 23, fontWeight: FontWeight.bold),
          ),
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              buildSettingDefaultForRoom(),
              // buildAddServiceForVoice()
            ],
          ),
        ));
  }

  Widget buildSettingDefaultForRoom() {
    return Card(
      elevation: 10,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            // decoration: const BoxDecoration(
            //   borderRadius: BorderRadius.only(topLeft: Radius.circular(10), topRight: Radius.circular(10))
            // ),
            width: double.infinity,
            color: Colors.deepOrangeAccent,
            child: const Padding(
              padding: EdgeInsets.all(16.0),
              child: Text("Giá trị mặc định cho các phòng",
                  style: TextStyle(
                      fontWeight: FontWeight.bold, color: Colors.black)),
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: buildContainerSetting(onPositionSelected),
          ),
        ],
      ),
    );
  }

  Widget buildContainerSetting(void Function(int) onPositionSelected) {
    const listItem = ["Ngày cuối tháng","1","2","3","4","5","6","7","8"
      ,"9","10","11","12","13","14","15","16","17","18","19","20"
      ,"21","22","23","24","25"];
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const Text("Ngày thu tiền hàng tháng",
            style: TextStyle(fontWeight: FontWeight.bold, color: Colors.black)),
        const SizedBox(height: 4),
        CommonDropMenuItem(
            items:listItem, initialValue: index, offsetWidth: 70,onSelected:(it){
             if(it != null){
               onPositionSelected(listItem.indexOf(it));
             }
        }),
        const SizedBox(height: 8),
        CommonTextFieldNormal(
            controller: edtPriceElectricity,
            title: "Giá điện (đ/kWh) ",
            hintText: "Ví dụ: 3,000",
            keyboardType: TextInputType.number),
        const SizedBox(height: 8),
        CommonTextFieldNormal(
            controller: edtPriceWater,
            title: "Giá nước (đ/khối) ",
            hintText: "Ví dụ: 11,000",
            keyboardType: TextInputType.number),
        const SizedBox(height: 8),
        CommonTextFieldNormal(
            controller: edtPriceRoom,
            title: "Giá thuê (đ/tháng) ",
            hintText: "Ví dụ: 1,500,000",
            keyboardType: TextInputType.number),
        const SizedBox(height: 8),
        CommonTextFieldNormal(
            controller: edtPriceInternet,
            title: "Giá internet (đ/tháng) ",
            hintText: "Ví dụ: 30,000",
            keyboardType: TextInputType.number),
        const SizedBox(height: 8),
        CommonTextFieldNormal(
            controller: edtPriceSecurity,
            title: "Giá an ninh (đ/tháng) ",
            hintText: "Ví dụ: 50,000",
            keyboardType: TextInputType.number ),
        const SizedBox(height: 8),
        Center(
            child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: CommonButton(
              nameButton: "Cập nhập",
              onClick: () {
                controller.saveData(SettingHouseModel(
                    index,
                    int.parse(edtPriceElectricity.text),
                    int.parse(edtPriceWater.text),
                    int.parse(edtPriceRoom.text),
                    int.parse(edtPriceInternet.text),
                    int.parse(edtPriceSecurity.text)));
              }),
        ))
      ],
    );
  }

// Widget buildAddServiceForVoice() {
//   return Card(
//       elevation: 10,
//       child: Column(
//           mainAxisAlignment: MainAxisAlignment.start,
//           crossAxisAlignment: CrossAxisAlignment.start,
//           children: [
//             Container(
//               width: double.infinity,
//               color: Colors.deepOrangeAccent,
//               child: const Padding(
//                 padding: EdgeInsets.all(16.0),
//                 child: Text("Thêm dịch vụ trên hóa đơn",
//                     style: TextStyle(
//                         fontWeight: FontWeight.bold, color: Colors.black)),
//               ),
//             ),
//             Visibility(
//               visible: data.isEmpty,
//               child:
//               const Center(
//                   child: Padding(
//                     padding: EdgeInsets.all(20.0),
//                     child: Text(
//                         "Hiện nhà chưa được cài đặt dịch vụ thêm. Bạn hãy nhấn nút + để thêm dịch vụ mới"),
//                   )),
//
//             ),
//             ListView.builder(
//               shrinkWrap: true,
//               itemCount: data.length,
//               itemBuilder: (context, index) {
//                 return Text(data[index]);
//               },
//             ),
//             Center(
//               child: Padding(
//                 padding: const EdgeInsets.all(16.0),
//                 child: InkWell(
//                   onTap: () {
//                     setState(() {
//                       data.add("data ");
//                     });
//                   },
//                   child: Container(
//                     height: 30,
//                     width: 30,
//                     decoration: const BoxDecoration(
//                         shape: BoxShape.circle, color: Colors.red),
//                     child: const Icon(Icons.add),
//                   ),
//                 ),
//               ),
//             )
//           ]));
// }
}
