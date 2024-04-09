
import 'package:datn/common/CommonTextFieldElecAndWater.dart';
import 'package:flutter/material.dart';

class CommonTextElectricAndWater extends StatelessWidget {
  late String title;
  late TextEditingController editingController;
  late String label;

  CommonTextElectricAndWater({super.key,  required this.title,  required this.label, required this.editingController});

  @override
  Widget build(BuildContext context) {
    editingController.value = TextEditingValue(text: label);
    return SizedBox(
        width: 170,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(title,style: const TextStyle(fontWeight: FontWeight.bold)),
            CommonTextFieldElecAndWater(
              label: label,
              editingController: editingController,
              valueFooter: "0/6",
            )
          ],
        ));
  }
}