#!/usr/bin/env wolframscript
(* ::Package:: *)

(*
This program does the operations of "building up" each set of cyclotomic integers with a certain complexity, where the cyclotomic integers are in Q[zeta_k]
*)
k = 3;

\[Zeta] = E ^ (2 \[Pi] I / k);

S = {{\[Zeta]}};

alreadyIn[set_List, search_] := 
(For[i = 1, i < Length[set] + 1, i++,
	For[j = 1, j < Length[set[[i]]] + 1, j++,
		If[set[[i]][[j]] == search,
			Return[True];
		];
	];
];
Return[False];)

f[comp_Integer] := (sComp = {}; For[ind1 = 1, ind1 <= comp / 2, ind1++,
        s1 = S[[ind1]];
        ind2 = comp - ind1;
        s2 = S[[ind2]];
        For[ele2ind = 1, ele2ind < Length[s2] + 1, ele2ind++,
            For[ele1ind = 1, ele1ind < Length[s1] + 1, ele1ind++,
                sum = s2[[ele2ind]] + s1[[ele1ind]];
                product = s2[[ele2ind]] * s1[[ele1ind]];
                (*
                Print[sum];
                Print[product];
                *)
                If[!alreadyIn[Append[S,sComp], sum],
                    AppendTo[sComp, sum];
                ];
                If[!alreadyIn[Append[S,sComp], product],
                    AppendTo[sComp, product];
                ];
            ];
        ];
    ]; AppendTo[S, sComp];);

Map[f,Range[2,10]];

data = "x y\n";
For[i = 1, i < Length[S] + 1, i++, 
	For[j = 1, j < Length[S[[i]]] + 1, j++, 
		If[IntegerQ[S[[i]][[j]]],
			data = StringJoin[{data, ToString[S[[i]][[j]]], ", ", ToString[i], "\n"}];
		]
	]
]
Export[StringJoin["k", ToString[k], ".txt"], data];



ca[num_Integer, comp_] := comp / Log[num];

max = 0;
For[i = 1, i < Length[S] + 1, i++, 
	For[j = 1, j < Length[S[[i]]] + 1, j++, 
		If[IntegerQ[S[[i]][[j]]],
			If[S[[i]][[j]] != 1 && ca[S[[i]][[j]], i] > max, 
			Print[S[[i]][[j]], ", ", i];
			max = ca[S[[i]][[j]], i];
			]
		]
	]
]
