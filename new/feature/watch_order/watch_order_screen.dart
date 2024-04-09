import 'package:datn/feature/list_detail_order/list_detail_order_screen.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class WatchOrderScreen extends StatefulWidget {
  const WatchOrderScreen({super.key});

  @override
  State<WatchOrderScreen> createState() => _WatchOrderScreenState();
}

class _WatchOrderScreenState extends State<WatchOrderScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        resizeToAvoidBottomInset: false,
        appBar: AppBar(
          title: const Text(
            "Hóa đơn",
            style: TextStyle(
                color: Colors.black, fontSize: 23, fontWeight: FontWeight.bold),
          ),
          leading: IconButton(
            icon: const Icon(Icons.arrow_back),
            onPressed: () {
              Get.off(const ListDetailOrderScreen());
            },
          ),
        ),
        body: Padding(
          padding: const EdgeInsets.all(16.0),
          child: SingleChildScrollView(
            scrollDirection: Axis.vertical,
            child: Card(
              elevation: 8.0,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  Container(
                    color: Colors.deepOrangeAccent,
                    height: 50,
                    child: Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [Text(Get.parameters['nameRoom'] ?? "")],
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(16.0),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [const Text("Ngày lập hóa đơn"), Text(Get.parameters["dateOfBill"] ?? "")],
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 16.0),
                    child: buildPersonalInfo(),
                  )
                ],
              ),
            ),
          ),
        ));
  }

  Widget buildPersonalInfo() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const Text("Thông tin", style:
          TextStyle(
            fontWeight: FontWeight.bold
          )
          ,),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text("Họ tên"),
            const Divider(
              height: 1,
            ),
            Text(Get.parameters["customer"] ?? "")
          ],
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text("Số điện thoại"),
            const Divider(
              height: 1,
            ),
            Text(Get.parameters["phone"] ?? "")
          ],
        ),
        const SizedBox(
          height: 10,
        ),
        const Text("Thông tin điện nước",
            style:
            TextStyle(
                fontWeight: FontWeight.bold
            )
        ),
        buildComonInfoElectric("Nước", "Số nước cũ", "Số nước mới",
            "Số nước sử dụng", "Giá nước", "Tiền nước",
            Get.parameters["numberWaterOld"]??"0",
            Get.parameters["numberWaterNew"]??"0",
            "${int.parse(Get.parameters["numberWaterNew"] ?? "0") - int.parse(Get.parameters["numberWaterOld"] ?? "0")}",
            Get.parameters[""]??"0",
            Get.parameters["totalMoneyWater"]??"0"),
        const SizedBox(
          height: 10,
        ),
        buildComonInfoElectric("Điện", "Số điện cũ", "Số điện mới",
            "Số điện sử dụng", "Giá điện", "Tiền điện",
            Get.parameters["numberElectricOld"]??"0",
            Get.parameters["numberElectricNew"]??"0",
            "${int.parse(Get.parameters["numberElectricNew"] ?? "0") - int.parse(Get.parameters["numberElectricOld"] ?? "0")}",
            Get.parameters["numberElectricNew"]??"0",
            Get.parameters["totalMoneyElectric"]??"0"
        ),
        const SizedBox(
          height: 10,
        ),
        const Text("Chi phí dịch vụ thêm",style:
        TextStyle(
            fontWeight: FontWeight.bold
        )),
        const SizedBox(
          height: 5,
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text("Tiền phòng"),
            Divider(
              height: 1,
            ),
            Text("0đ")
          ],
        ),
        SizedBox(
          height: 5,
        ),
        Padding(
          padding: const EdgeInsets.only(bottom: 16.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text("Tổng tiền",style:
              TextStyle(
                  fontWeight: FontWeight.bold
              )),
              Divider(
                height: 1,
              ),
              Text(Get.parameters["totalMoneyToPay"] ?? "")
            ],
          ),
        ),
      ],
    );
  }

  Widget buildComonInfoElectric(
      String titleCommon,
      String title1,
      String title2,
      String title3,
      String title4,
      String title5,
      String valueTitle1,
      String valueTitle2,
      String used,
      String moneyOfTitle,
      String totalMoneyOfTitle) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(titleCommon,style:
        TextStyle(
            fontWeight: FontWeight.bold
        )),
        const SizedBox(
          height: 8,
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(title1),
            const Divider(
              height: 1,
            ),
            Text("$valueTitle1")
          ],
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(title2),
            const Divider(
              height: 1,
            ),
            Text("$valueTitle2")
          ],
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(title3),
            const Divider(
              height: 1,
            ),
            Text("$used")
          ],
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(title4),
            const Divider(
              height: 1,
            ),
            Text("$moneyOfTitle")
          ],
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(title5),
            const Divider(
              height: 1,
            ),
            Text("$totalMoneyOfTitle")
          ],
        )
      ],
    );
  }
}
