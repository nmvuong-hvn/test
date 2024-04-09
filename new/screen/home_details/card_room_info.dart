import 'package:datn/constant/constants.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../../common/CommonButton.dart';


class CardRoomInfo extends StatelessWidget {


  final String nameRoom ;
  final String? nameUser ;
  final String? numberPhone ;
  final String? dayReceivedHouse;
  final String? money ;
  final void Function() selected ;
  final void Function(Item) selectChangeRoom ;
  final void Function()? createBills ;


  const CardRoomInfo({
    super.key,
    required this.nameRoom,
    required this.selected,
    this.numberPhone,
    this.dayReceivedHouse,
    this.money,
    this.nameUser,
    required this.selectChangeRoom,
    this.createBills

  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Padding(
            padding: const EdgeInsets.symmetric(vertical: 8.0),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(nameRoom,
                  style: const TextStyle(
                      color: Colors.red,
                      fontSize: 17,
                      fontWeight: FontWeight.bold
                  ),
                ),
                PopupMenuButton(
                    onSelected: (Item data) {
                      selectChangeRoom(data);
                    },
                    itemBuilder: (BuildContext context) =>
                    <PopupMenuEntry<Item>>[
                      PopupMenuItem<Item>(
                        value: Item.Change,
                        child: Text(Item.Change.name),
                      ),
                      PopupMenuItem<Item>(
                        value: Item.Order,
                        child: Text(Item.Order.name),
                      ),
                    ]
                )
              ],
            ),
          ),
          const SizedBox(height: 10),
          showInfo(nameUser,numberPhone,dayReceivedHouse,money),
        ],
      ),
    );
  }

  Widget showInfo(final String? name, String? numberPhone,
      String? day, String? money){

    if (name?.isNotEmpty == true && day?.isNotEmpty == true) {
      return  Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const SizedBox(width: 120, child: Text("Người thuê:")),
              SizedBox(width: 150, child: Text(name!))
            ],
          ),
          Row(mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const SizedBox(width: 120, child: Text("Số điện thoại:")),
              SizedBox(width: 150, child: Text(numberPhone ??""))
            ],
          ),
          Row(mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const SizedBox(width: 120, child: Text("Ngày thuê:")),
              SizedBox(width: 150, child: Text(day!))
            ],
          ),
          Row(mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const SizedBox(width: 120, child: Text("Số tiền còn nợ:")),
              SizedBox(width: 150, child: Text(money ?? "0 đ"))
            ],
          ),
          const SizedBox(height: 10),
          Center(
            child: CommonButton(nameButton: "Tạo hóa đơn", onClick: (){
              if(createBills != null) {
                createBills!();
              }
            }),
          ),
          const SizedBox(height: 5),
        ],
      );
    } else {
      return Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text("Trống",
            style: TextStyle(
                color: Colors.black,
                fontSize: 17,
                fontWeight: FontWeight.bold
            ),
          ),
          const SizedBox(height: 10),
          Center(
            child: CommonButton(nameButton: "Thêm khách", onClick: (){
              selected();
            }),
          ),
          const SizedBox(height: 5),
        ],
      );
    }
  }
}
