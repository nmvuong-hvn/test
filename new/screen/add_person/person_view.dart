import 'package:datn/screen/add_person/person_controller.dart';
import 'package:datn/screen/add_person/person_model.dart';
import 'package:datn/screen/home_details/home_details.dart';
import 'package:datn/screen/home_details/home_details_controller.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';

import '../../common/CommonTexFieldNormal.dart';
import '../../common/ComonPickerTimeAndEdt.dart';
import '../../constant/color.dart';

class PersonView extends StatefulWidget {
  const PersonView({super.key});

  @override
  State<PersonView> createState() => _PersonViewState();
}

class _PersonViewState extends State<PersonView> {

  final edtName =  TextEditingController(text: "");
  final edtNumberPhone =  TextEditingController(text: "");
  final edtAddress =  TextEditingController(text: "");
  final edtCCCD =  TextEditingController(text: "");
  final edtMoney =  TextEditingController(text: "");
  int day = DateTime.now().microsecondsSinceEpoch;
  final edtNote =  TextEditingController(text: "");
  final controller = Get.put(PersonController());
  String dateTime = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        title: const Text(
          "Thêm khách",
          style: TextStyle(
              color: Colors.black,
              fontSize: 23,
              fontWeight: FontWeight.bold),
        ),
      ),
      body: Column(
        children: [
          Expanded(
            child: SingleChildScrollView(
              child: Padding(
                padding: const EdgeInsets.symmetric(horizontal: 16,vertical: 16),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      padding: const EdgeInsets.all(10),
                      decoration: BoxDecoration(
                          color: ColorInstance.backgroundColor,
                          borderRadius: const BorderRadius.only(
                              topLeft: Radius.circular(15),
                              topRight: Radius.circular(15))),
                      child: const Center(
                        child: Text(
                          "Tên phòng",
                          style: TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontWeight: FontWeight.bold),
                        ),
                      ),
                    ),
                    Container(
                      padding: const EdgeInsets.all(10),
                      decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius: const BorderRadius.only(
                            bottomLeft: Radius.circular(15),
                            bottomRight: Radius.circular(15)),
                        boxShadow: [
                          BoxShadow(
                            color: Colors.grey.withOpacity(0.5),
                            // Màu sắc của bóng đổ
                            spreadRadius: 5,
                            // Độ rộng của bóng đổ
                            blurRadius: 7,
                            // Độ mờ của bóng đổ
                            offset: Offset(0, 3), // Thay đổi vị trí của bóng đổ
                          ),
                        ],
                      ),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          const SizedBox(height: 10),
                          CommonTextFieldNormal(
                              title: "Khách thuê",
                              hintText: "VD: Nguyễn Văn A",
                              keyboardType: TextInputType.text,
                              controller: edtName,),
                          const SizedBox(height: 10),
                          CommonTextFieldNormal(
                              title: "Số điện thoại",
                              hintText: "VD: 0358227339",
                              keyboardType: TextInputType.phone,
                              controller: edtNumberPhone),
                          const SizedBox(height: 10),
                          CommonTextFieldNormal(
                              title: "Địa chỉ",
                              hintText: "VD: Hà Nội",
                              keyboardType: TextInputType.text,
                              controller: edtAddress),
                          const SizedBox(height: 10),
                          CommonTextFieldNormal(
                              title: "CMND/CCCD",
                              hintText: "VD: 125971144",
                              keyboardType: TextInputType.number,
                              controller: edtCCCD),
                          const SizedBox(height: 10),
                          Row(
                            children: [
                              Expanded(
                                  child: Row(
                                    children: [
                                      const Text("CMND trước",
                                          style: TextStyle(
                                              fontWeight: FontWeight.bold,
                                              fontSize: 15
                                          )
                                      ),
                                      const SizedBox(width: 15),
                                      InkWell(
                                          child: Icon(Icons.camera_alt),
                                          onTap: (){
                                            controller.getImageBefore();
                                          }),
                                    ],
                                  )
                              ),
                              Expanded(
                                  child: Row(
                                    children: [
                                      const Text("CMND sau",style: TextStyle(
                                          fontWeight: FontWeight.bold,
                                          fontSize: 15
                                      )),
                                      const SizedBox(width: 15),
                                      InkWell(
                                          onTap: ()=> controller.getImageAfter(),
                                          child: const Icon(Icons.camera_alt))
                                    ],
                                  )
                              )
                            ],
                          ),
                          const SizedBox(height: 10),
                           Row(
                             crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              SizedBox(
                                height: 100,
                                width: 150,
                                child: Obx( () => Image.network(
                                    controller.imageCCCDBefore.value.isEmpty?
                                  'https://cdn-icons-png.flaticon.com/512/1160/1160358.png' :
                                        controller.imageCCCDBefore.value
                                  ,
                                  ),
                                ),
                              ),
                              const SizedBox(width: 10),
                              SizedBox(
                              height: 100,
                              width: 150,
                                child: Obx( ()=> Image.network(
                                      controller.imageCCCDAfter.value.isEmpty ?
                                  'https://cdn-icons-png.flaticon.com/512/1160/1160358.png' :
                                      controller.imageCCCDAfter.value,
                                  ),
                                ),
                              )
                            ],
                          ),
                          const SizedBox(height: 10),
                          CommonTextFieldNormal(
                              title: "Tiền cọc",
                              hintText: "VD: 1,500,000",
                              keyboardType: TextInputType.number,
                              controller: edtMoney),
                          const SizedBox(height: 10),
                          const Text("Ngày thuê",style: TextStyle(fontWeight: FontWeight.bold,fontSize: 15,
                          )),
                          const SizedBox(height: 3),
                          CommonPickerTimeAndEdt(
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
                          const SizedBox(height: 10),
                          customText("Giá điện (đ/kWh)","4,000",TextInputType.number),
                          const SizedBox(height: 10),
                          customText("Giá nước (đ/khối)","25,000",TextInputType.number),
                          const SizedBox(height: 10),
                          customText("Giá internet (đ/tháng)","100,000",TextInputType.number),
                          const SizedBox(height: 10),
                          customText("Giá ANQP (đ/tháng)","50,000",TextInputType.number),
                          const SizedBox(height: 10),
                          customText("Giá thuê (đ/tháng)","2,500,000",TextInputType.number),
                          const SizedBox(height: 10),
                          const Text("Ghi chú",style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 15
                          )),
                          TextField(
                            controller: edtNote,
                            decoration: const InputDecoration(
                                contentPadding: EdgeInsets.symmetric(
                                    vertical: 40, horizontal: 13),
                                hintText: "VD: Phòng thuê ở 3 người",
                              floatingLabelBehavior: FloatingLabelBehavior.always,
                            ),
                            keyboardType: TextInputType.text,
                          ),
                          const SizedBox(height: 15),
                          Center(
                            child: SizedBox(
                              width: 120,
                              child: ElevatedButton(
                                onPressed: () {
                                  var id = DateTime.now().microsecondsSinceEpoch.toString();
                                  PersonModel personModel = PersonModel(id, edtName.text , edtNumberPhone.text,
                                      edtAddress.text, edtCCCD.text, controller.imageCCCDBefore.value,
                                      controller.imageCCCDAfter.value, edtNumberPhone.text,
                                      dateTime.isEmpty ? DateFormat('dd/MM/yyyy').format(DateTime.now()) : dateTime,
                                      edtMoney.text, edtNote.text);
                                  controller.addPerson(
                                      Get.parameters['idRoom']??"",
                                      personModel,
                                      {
                                        'idRoom' : Get.parameters['idRoom']??"",
                                        'nameRoom': Get.parameters['nameRoom']??"",
                                        'idHome': Get.parameters['idHome']??"",
                                        'isEmpty': "true"
                                      });
                                  Get.delete<HomeDetailsController>();
                                  Get.off(HomeDetailView());
                                },
                                style:
                                ElevatedButton.styleFrom(backgroundColor: ColorInstance.backgroundColor),
                                child: const Text("Lưu lại",
                                    style: TextStyle(
                                        color: Colors.white,
                                        fontSize: 18,
                                        fontWeight: FontWeight.bold)),
                              ),
                            ),
                          ),
                        ],
                      ),
                    )
                  ],
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
  Widget customText(String title, String content,TextInputType keyboardType){
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(title,style: const TextStyle(
            fontWeight: FontWeight.bold,
            fontSize: 15
        )),
        const SizedBox(height: 3),
        TextField(
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            contentPadding: const EdgeInsets.symmetric(
                vertical: 13, horizontal: 13),
            hintText: content,
            hintStyle: const TextStyle(
              color: Colors.black,
              fontWeight: FontWeight.normal
            )
          ),
          keyboardType: keyboardType,
        )
      ],
    );
  }
}
