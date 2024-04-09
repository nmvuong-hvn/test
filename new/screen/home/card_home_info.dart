
import 'dart:ui';

import 'package:datn/common/CommonButton.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class CardHomeInfo extends StatelessWidget {
  final String nameHome ;
  final String addressHome ;
  final int numberRoom ;
  final int numberRoomEmpty ;
  final int numberRoomShortage ;
  final int numberMoney ;
  final void Function() selected ;
  const CardHomeInfo({super.key,
    required this.nameHome,
    required this.addressHome,
    required this.numberRoom,
    required this.numberRoomEmpty,
    required this.numberRoomShortage,
    required this.numberMoney,
    required this.selected});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16.0),
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
                Text(nameHome,
                style: const TextStyle(
                  color: Colors.red,
                  fontSize: 17,
                  fontWeight: FontWeight.bold
                ),
                ),
                const Icon(Icons.more_vert)
              ],
            ),
          ),
           Row(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const Icon(Icons.location_on, color: Colors.red,),
              const SizedBox(width: 5),
              Text(addressHome,style: TextStyle(
                fontSize: 15
              ),)
            ],
          ),
          const SizedBox(height: 10,),
           Row(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Expanded(
                flex: 9,
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        const Expanded(flex: 2 ,child: Text("Số phòng: ",style: TextStyle(
                            fontSize: 15
                        ))),
                        Expanded(flex: 2 ,child: Text("$numberRoom",style: const TextStyle(
                            fontSize: 17
                        )))
                      ],
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        const Expanded(flex: 2, child: Text("Số phòng trống: ",style: TextStyle(
                            fontSize: 15
                        ))),
                        Expanded(flex: 2,child: Text("$numberRoomEmpty",style: const TextStyle(
                            fontSize: 17
                        )))
                      ],
                    ),

                    Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        const Expanded(flex: 2, child: Text("Số phòng thiếu tiền: ",style: TextStyle(
                            fontSize: 15
                        ))),
                        Expanded(flex: 2,child: Text("$numberRoomShortage",style: const TextStyle(
                            fontSize: 17
                        ))
                        )
                      ],
                    ),
                    const Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Expanded(flex: 2 , child: Text("Số tiền còn thừa: ",style: TextStyle(
                            fontSize: 15
                        ))),
                        Expanded(flex: 2 , child: Text(""))
                      ],
                    ),
                  ],
                ),
              ),
            ],
          ),
          const SizedBox(height: 5),
          const Divider(height: 1, color: Colors.grey,),
          const SizedBox(height: 8),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  const Text("Doang thu tháng:",style: TextStyle(
                      fontSize: 15
                  )),
                  Text("$numberMoney",style: const TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: 17
                  ))
                ],
              ),
              CommonButton(nameButton: "Chi tiết", onClick: (){
                  selected();
              }),

          ],),
          const SizedBox(height: 5),
        ],
      ),
    );
  }
  
  
}
