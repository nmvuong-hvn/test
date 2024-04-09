
import 'package:flutter/material.dart';

class CommonTextFieldElecAndWater extends StatefulWidget {
  final TextEditingController? editingController;
  final Color? colorBorder;
  final String? label;
  final String? valueFooter;

   const CommonTextFieldElecAndWater(
      {super.key, this.editingController, this.colorBorder, this.label, this.valueFooter});

  @override
  State<CommonTextFieldElecAndWater> createState() => _CommonTextFieldElecAndWater();
}

class _CommonTextFieldElecAndWater extends State<CommonTextFieldElecAndWater> {
  Color? color;
  String valueFooter = "";

  @override
  void initState() {
    super.initState();
    color = widget.colorBorder;
    valueFooter = widget.valueFooter ?? "";
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.fromLTRB(0,8,0,0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.end,
        crossAxisAlignment: CrossAxisAlignment.end,
        children: [
          TextField(
            controller: widget.editingController,
            decoration: InputDecoration(
              hintText: widget.label,
              border: OutlineInputBorder(
                  borderSide:
                  BorderSide(color: (color != null) ? color! : Colors.grey)),
            ),
          ),
          Padding(
            padding: const EdgeInsets.fromLTRB(0, 4, 8, 0),
            child: Text(valueFooter),
          )
        ],
      ),
    );
  }
}
