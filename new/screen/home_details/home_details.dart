import 'package:datn/AppRoutes.dart';
import 'package:datn/constant/asset_image.dart';
import 'package:datn/constant/color.dart';
import 'package:datn/feature/create_bill/create_bill.dart';
import 'package:datn/feature/create_electric_water/create_electric_water.dart';
import 'package:datn/feature/list_detail_order/list_detail_order_screen.dart';
import 'package:datn/feature/setting_house/setting_house.dart';
import 'package:datn/feature/watch_order/watch_order_screen.dart';
import 'package:datn/screen/add_person/person_view.dart';
import 'package:datn/screen/home/home_view.dart';
import 'package:datn/screen/home_details/card_room_info.dart';
import 'package:datn/screen/home_details/home_details_controller.dart';
import 'package:datn/screen/home_details/room_model.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../../constant/constants.dart';
import '../add_person/person_model.dart';

class HomeDetailView extends StatefulWidget {
  const HomeDetailView({super.key});

  @override
  State<HomeDetailView> createState() => _HomeDetailViewState();
}

class _HomeDetailViewState extends State<HomeDetailView> {
  final edtNameRoomController = TextEditingController();
  final edtSearchController = TextEditingController();
  final controllerHomeDetails = Get.put(HomeDetailsController());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        title: Text(
          Get.parameters['nameHome'] ?? "Tên Nhà",
          style: const TextStyle(
              color: Colors.black, fontSize: 23, fontWeight: FontWeight.bold),
        ),
        leading: IconButton(
          icon: Icon(Icons.arrow_back),
          onPressed: () {
            Get.off(HomeView());// Quay trở lại màn hình trước đó
          },
        ),
      ),
      body: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              CustomIconButton(
                image: AssetsImage.instance.imgAddHome,
                text: "Thêm phòng",
                onPressed: () {
                  showDialogAddRoom(context);
                },
              ),
              CustomIconButton(
                image: AssetsImage.instance.imgLamp,
                text: "Ghi điện nước",
                onPressed: () {
                  Get.to(const CreateElectricAndWaterScreen());
                },
              ),
              CustomIconButton(
                image: AssetsImage.instance.imgSetting,
                text: "Cài đặt",
                onPressed: () {
                  Get.to(const SettingHouseScreen());
                },
              )
            ],
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 16.0),
            child: SizedBox(
              height: 50,
              child: TextFormField(
                controller: edtSearchController,
                onChanged: (String value) {
                  controllerHomeDetails.searchListRoom(value);
                },
                decoration: InputDecoration(
                  prefixIcon: const Icon(Icons.search, size: 25),
                  hintText: "Tìm kiếm.....",
                  hintStyle: TextStyle(color: ColorInstance.backgroundColor),
                ),
                textAlignVertical: TextAlignVertical.bottom,
              ),
            ),
          ),
          Expanded(
              child: Obx(
            () => edtSearchController.text.isNotEmpty &&
                controllerHomeDetails.searchList.value.isEmpty
                ? Center(
              child: Padding(
                padding: const EdgeInsets.all(15.0),
                child: Text(
                  "Không tìm thấy dữ liệu !",
                  style: TextStyle(
                      color: ColorInstance.backgroundColor,
                      fontSize: 25,
                      fontWeight: FontWeight.bold),
                ),
              ),
            )
                : ListView.builder(
                itemCount:controllerHomeDetails.searchList.value.isNotEmpty
                    ? controllerHomeDetails.searchList.value.length
                    : controllerHomeDetails.roomList.value.length,
                itemBuilder: (context, index) {
                  final roomDataLocal =
                  controllerHomeDetails.searchList.value.isNotEmpty
                      ? controllerHomeDetails.searchList.value[index]
                      : controllerHomeDetails.roomList.value.toList()[index];
                  return Container(
                    padding: const EdgeInsets.symmetric(horizontal: 16.0),
                    margin: const EdgeInsets.only(top: 10,bottom: 5),
                    child: Container(
                      decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius:
                            const BorderRadius.all(Radius.circular(15)),
                        boxShadow: [
                          BoxShadow(
                            color: Colors.grey.withOpacity(0.5),
                            spreadRadius: 3,
                            blurRadius: 5,
                            offset: const Offset(0, 3),
                          ),
                        ],
                      ),
                      child: Padding(
                        padding: const EdgeInsets.symmetric(vertical: 5),
                        child: InkWell(
                            onTap: () {},
                            child: Obx( ()=> CardRoomInfo(
                                nameRoom: roomDataLocal.nameRoom,
                                nameUser: controllerHomeDetails.personMap.value[roomDataLocal.idRoom] == null ? "" :
                                controllerHomeDetails.personMap.value[roomDataLocal.idRoom]?.name,
                                numberPhone: controllerHomeDetails.personMap.value[roomDataLocal.idRoom] == null ? "" :
                                controllerHomeDetails.personMap.value[roomDataLocal.idRoom]?.phone,
                                dayReceivedHouse: controllerHomeDetails.personMap.value[roomDataLocal.idRoom]?.dayOfRent,
                                money: "0",
                                selected: () {
                                  var dataModel = {
                                    'idRoom': roomDataLocal.idRoom,
                                    'nameRoom':roomDataLocal.nameRoom,
                                    'idHome': roomDataLocal.idHome,
                                    'isEmpty': roomDataLocal.isEmpty
                                  };
                                  Get.toNamed(AppRoutes.addPersonRoute,parameters: dataModel);
                                  FocusManager.instance.primaryFocus?.unfocus(); //an focus ban phim

                                },
                                selectChangeRoom: (item){
                                  if(item == Item.Change){
                                    print("room = $roomDataLocal");
                                    controllerHomeDetails.changeInfoRoom(roomDataLocal);
                                  }
                                  if(item == Item.Order){
                                    var dataModel = {
                                      'idRoom': roomDataLocal.idRoom,
                                      'nameRoom':roomDataLocal.nameRoom,
                                      'customer' : (controllerHomeDetails.personMap.value[roomDataLocal.idRoom]?.name) ?? "",
                                      'phone' : (controllerHomeDetails.personMap.value[roomDataLocal.idRoom]?.phone) ?? "",
                                    };
                                    Get.toNamed(AppRoutes.listOrderRoute, parameters: dataModel);
                                  }
                                },
                                createBills: (){
                                  var data = {
                                    'idRoom' : roomDataLocal.idRoom,
                                    'nameRoom' : roomDataLocal.nameRoom
                                  };
                                  Get.toNamed(AppRoutes.createBillRoute,parameters: data);
                                },
                              ),
                            )),
                      ),
                    ),
                  );
                }),
          )),
        ],
      ),
    );
  }

  void showDialogAddRoom(BuildContext context) {
    AlertDialog alert = AlertDialog(
      titlePadding: const EdgeInsets.all(0),
      title: Container(
        padding: const EdgeInsets.all(15),
        decoration: BoxDecoration(
            color: ColorInstance.backgroundColor,
            borderRadius: const BorderRadius.only(
                topLeft: Radius.circular(15), topRight: Radius.circular(15))),
        child: const Center(
          child: Text(
            "Thông tin phòng",
            style: TextStyle(
                color: Colors.white, fontSize: 20, fontWeight: FontWeight.bold),
          ),
        ),
      ),
      content: Builder(builder: (context) {
        return SizedBox(
          width: MediaQuery.of(context).size.width * 0.8,
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              const SizedBox(height: 10),
              TextFormField(
                controller: edtNameRoomController,
                decoration: InputDecoration(
                    labelText: "Tên nhà",
                    contentPadding: const EdgeInsets.symmetric(
                        vertical: 15, horizontal: 15),
                    labelStyle:
                        TextStyle(color: ColorInstance.backgroundColor)),
              ),
              const SizedBox(height: 20),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  SizedBox(
                    width: 120,
                    child: ElevatedButton(
                      onPressed: () {
                        Navigator.of(context).pop();
                      },
                      style:
                          ElevatedButton.styleFrom(backgroundColor: Colors.red),
                      child: const Text("Huỷ bỏ",
                          style: TextStyle(
                              color: Colors.white,
                              fontSize: 18,
                              fontWeight: FontWeight.bold)),
                    ),
                  ),
                  const SizedBox(width: 20),
                  SizedBox(
                    width: 120,
                    child: ElevatedButton(
                      onPressed: () {
                        RoomModel roomModel = RoomModel(
                            "",
                            Get.parameters['idHome'] ?? "",
                            edtNameRoomController.text, "false");
                        controllerHomeDetails.addRoom(roomModel);
                        Navigator.of(context).pop();
                      },
                      child: const Text("Lưu lại",
                          style: TextStyle(
                              color: Colors.white,
                              fontSize: 18,
                              fontWeight: FontWeight.bold)),
                    ),
                  ),
                ],
              )
            ],
          ),
        );
      }),
    );
    showDialog(
      barrierDismissible: true,
      context: context,
      builder: (BuildContext context) {
        return alert;
      },
    );
  }
}

class CustomIconButton extends StatelessWidget {
  final String image;
  final String text;
  final VoidCallback onPressed;

  const CustomIconButton({
    required this.image,
    required this.text,
    required this.onPressed,
  });

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 75,
      width: 120,
      child: TextButton(
        onPressed: onPressed,
        child: Center(
          child: Column(
            children: [
              Image.asset(
                image,
                width: 30,
                height: 30,
              ),
              const SizedBox(height: 5),
              Text(
                text,
                style: const TextStyle(
                  fontSize: 14,
                  color: Colors.black,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
