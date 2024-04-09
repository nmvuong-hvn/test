import 'package:datn/AppRoutes.dart';
import 'package:datn/constant/asset_image.dart';
import 'package:datn/constant/color.dart';
import 'package:datn/constant/routes.dart';
import 'package:datn/firebase_helper/firebase_authentication/firebase_auth_helper.dart';
import 'package:datn/screen/home/card_home_info.dart';
import 'package:datn/screen/home/home_controller.dart';
import 'package:datn/screen/home/home_model.dart';
import 'package:datn/screen/login/signin/signin.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:lottie/lottie.dart';

class HomeView extends StatefulWidget {
  const HomeView({super.key});

  @override
  State<HomeView> createState() => _HomeViewState();
}

class _HomeViewState extends State<HomeView> {
  final edtNameHomController = TextEditingController();
  final edtAddressHomController = TextEditingController();
  final edtSearchController = TextEditingController();
  final controller = Get.put(HomeController());

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    print('init state flutter');
    controller.onInit();
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      floatingActionButton: InkWell(
          onTap: () {
            showDialogAddHome(context);
          },
          child: SizedBox(
              width: 65,
              height: 65,
              child: Lottie.asset('assets/json/button_add.json'))),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 15, vertical: 10),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              InkWell(
                onTap: () {
                  FirebaseAuthHelper.instance.signOut();
                  Routes.instance
                      .pushAndRemoveUntil(widget: Signin(), context: context);
                },
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    Padding(
                      padding: const EdgeInsets.all(10.0),
                      child: Image.asset(AssetsImage.instance.imgLogout,
                          height: 25, width: 25),
                    ),
                  ],
                ),
              ),
              Center(
                  child: SizedBox(
                      height: 150,
                      child: Lottie.asset('assets/json/home.json'))),
              Text(
                "Danh sách nhà trọ",
                style: TextStyle(
                    fontSize: 23,
                    fontWeight: FontWeight.bold,
                    fontFamily: 'Handle',
                    color: ColorInstance.backgroundColor),
              ),
              const SizedBox(height: 5),
              SizedBox(
                height: 50,
                child: TextFormField(
                  controller: edtSearchController,
                  onChanged: (String value) {
                    controller.searchListHome(value);
                  },
                  decoration: InputDecoration(
                    prefixIcon: const Icon(Icons.search, size: 25),
                    hintText: "Tìm kiếm.....",
                    hintStyle: TextStyle(color: ColorInstance.backgroundColor),
                  ),
                  textAlignVertical: TextAlignVertical.bottom,
                ),
              ),
              Expanded(
                  child: Obx(() =>edtSearchController.text.isNotEmpty &&
                          controller.searchList.value.isEmpty
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
                      :  ListView.builder(
                      itemCount: controller.searchList.value.isNotEmpty
                          ? controller.searchList.value.length
                          : controller.homesList.value.length ,
                      itemBuilder: (context, index) {
                        final homeDataLocal = controller.searchList.value.isNotEmpty
                            ? controller.searchList.value[index]
                            : controller.homesList.value.toList()[index];
                        return Padding(
                          padding: const EdgeInsets.symmetric(vertical: 8,horizontal: 5),
                          child: Container(
                            decoration: BoxDecoration(
                                color: Colors.white,
                                borderRadius: const BorderRadius.all(Radius.circular(15)),
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
                                onTap: (){},
                                child: Obx(()=> CardHomeInfo(
                                    addressHome: homeDataLocal.addressHome ,
                                    nameHome: homeDataLocal.nameHome,
                                    numberRoom: controller.hashMap[controller.homesList.value.toList()[index].idHome]?.length ?? 0,
                                    // numberRoom: 0,
                                    numberRoomEmpty: controller.numberEmpty.value[homeDataLocal.idHome] ?? 0,
                                    numberRoomShortage: controller.numberShortage.value[homeDataLocal.idHome] ?? 0,
                                    numberMoney: controller.numberMoney.value[homeDataLocal.idHome] ?? 0,
                                    selected: (){
                                      controller.selectedHome(homeDataLocal);
                                      Get.toNamed(AppRoutes.homeDetailRoute, parameters: homeDataLocal.toHomeMap());
                                      FocusManager.instance.primaryFocus?.unfocus();//an focus ban phim

                                    },
                                  ),
                                )
                              ),
                            ),
                          ),
                        );
                      }))),
            ],
          ),
        ),
      ),
    );
  }

  void showDialogAddHome(BuildContext context) {
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
            "Thông tin nhà trọ",
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
                controller: edtNameHomController,
                decoration: InputDecoration(
                    labelText: "Tên nhà",
                    contentPadding: const EdgeInsets.symmetric(
                        vertical: 15, horizontal: 15),
                    labelStyle:
                        TextStyle(color: ColorInstance.backgroundColor)),
              ),
              const SizedBox(height: 20),
              TextFormField(
                controller: edtAddressHomController,
                decoration: InputDecoration(
                    labelText: "Địa chỉ",
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
                        HomeModel homeModel = HomeModel(
                            "",
                            edtNameHomController.text,
                            edtAddressHomController.text);
                        controller.createHome(homeModel);
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
