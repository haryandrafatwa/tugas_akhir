var matrix = [[0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,3,0,0,0,0,0,1,1,2,0,0,0,0,0,0,1,3,0,0,0,0,0,3],[1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0],[0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1],[0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11,0,0,0,0,0,1],[0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,2,0,0,0,0,0,1,1,0,0,0,0,3],[1,0,1,1,0,1,0,0,2,0,0,0,0,0,0,4,0,0,0,0,0,0,2,0,0,0,0,1,0,0,0,4,0,0,0,0,0,3],[0,0,0,0,0,3,0,0,0,0,0,0,0,0,4,7,0,0,0,0,0,1,3,2,0,0,0,0,0,0,0,8,1,0,0,0,0,3],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,13,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,2],[0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,0,0,0,0,2],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,10,0,0,0,0,0,15,0,5,0,0,0,0,0,5,14,0,0,0,0,0,0,0,3,15,0,5,0,0,15],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,5,0,0,0,0,5],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,5,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,4,1,0,0,0,0,4],[0,0,0,0,0,0,0,0,0,0,0,3,0,0,1,3,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,1,1,0,0,0,0,3],[0,0,0,0,0,7,0,0,0,4,0,0,0,0,0,9,1,0,0,0,0,0,4,6,0,10,0,0,0,0,0,5,1,0,0,0,0,4],[0,0,0,0,1,0,0,0,0,0,0,0,0,0,4,5,0,1,0,0,0,1,1,3,0,0,0,0,0,0,0,7,1,0,0,0,0,5],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,4,0,0,0,3,0,0,0,0,6,6,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,3,0,0,0,0,0,3],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11,4,0,0,0,0,0],[0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,4],[0,0,0,0,1,0,0,0,0,0,2,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1],[0,0,0,0,0,1,0,0,0,1,0,0,0,0,6,7,0,0,0,0,0,0,1,2,0,1,0,0,0,0,0,6,1,0,0,0,0,3],[0,0,0,0,5,0,0,0,0,0,0,0,0,0,3,10,0,1,0,0,0,3,0,10,0,0,0,0,0,0,0,6,1,0,0,0,0,8],[0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,1,0,0,0,0,3],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,3,0,0,0,0,0,5],[0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,6,0,0,0,0,0,3],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0]]
var packages = [{
"name": " org.d3ifcool.finpro.mahasiswa.fragments.parent", "color": " #3182bd"
}
,{
"name": " org.d3ifcool.finpro.adapters", "color": " #6baed6"
}
,{
"name": " org.d3ifcool.finpro.core.components", "color": " #9ecae1"
}
,{
"name": " org.d3ifcool.finpro.core.mediators", "color": " #c6dbef"
}
,{
"name": " org.d3ifcool.finpro.dosen.adapters.recyclerview", "color": " #e6550d"
}
,{
"name": " org.d3ifcool.finpro.core.mediators.prodi", "color": " #fd8d3c"
}
,{
"name": " org.d3ifcool.finpro.activities", "color": " #fdae6b"
}
,{
"name": " org.d3ifcool.finpro.prodi.activities.detail", "color": " #fdd0a2"
}
,{
"name": " org.d3ifcool.finpro.core.interfaces.objects", "color": " #31a354"
}
,{
"name": " org.d3ifcool.finpro.core.mediators.interfaces.prodi", "color": " #74c476"
}
,{
"name": " org.d3ifcool.finpro.dosen.adapters.viewpager", "color": " #a1d99b"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.adapters.recyclerview", "color": " #c7e9c0"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.fragments.child", "color": " #756bb1"
}
,{
"name": " org.d3ifcool.finpro.prodi.activities", "color": " #9e9ac8"
}
,{
"name": " org.d3ifcool.finpro.core.interfaces.works", "color": " #bcbddc"
}
,{
"name": " org.d3ifcool.finpro.core.presenters", "color": " #dadaeb"
}
,{
"name": " org.d3ifcool.finpro.core.models.manager", "color": " #636363"
}
,{
"name": " org.d3ifcool.finpro.dosen.activities.editor.update", "color": " #969696"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.activities", "color": " #bdbdbd"
}
,{
"name": " org.d3ifcool.finpro.prodi.fragments", "color": " #d9d9d9"
}
,{
"name": " org.d3ifcool.finpro.dosen.activities.detail", "color": " #3182bd"
}
,{
"name": " org.d3ifcool.finpro.core.adapters", "color": " #6baed6"
}
,{
"name": " org.d3ifcool.finpro.core.interfaces", "color": " #9ecae1"
}
,{
"name": " org.d3ifcool.finpro.core.interfaces.lists", "color": " #c6dbef"
}
,{
"name": " org.d3ifcool.finpro.prodi.activities.editor.create", "color": " #e6550d"
}
,{
"name": " org.d3ifcool.finpro.prodi.adapters", "color": " #fd8d3c"
}
,{
"name": " org.d3ifcool.finpro.dosen.fragments.child", "color": " #fdae6b"
}
,{
"name": " org.d3ifcool.finpro.dosen.fragments.parent", "color": " #fdd0a2"
}
,{
"name": " org.d3ifcool.finpro.prodi.activities.editor.update", "color": " #31a354"
}
,{
"name": " org.d3ifcool.finpro.dosen.activities", "color": " #74c476"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.adapters.viewpager", "color": " #a1d99b"
}
,{
"name": " org.d3ifcool.finpro.core.models", "color": " #c7e9c0"
}
,{
"name": " org.d3ifcool.finpro.core.api", "color": " #756bb1"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.activities.editor", "color": " #9e9ac8"
}
,{
"name": " org.d3ifcool.finpro", "color": " #bcbddc"
}
,{
"name": " org.d3ifcool.finpro.dosen.activities.editor.create", "color": " #dadaeb"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.activities.detail", "color": " #636363"
}
,{
"name": " org.d3ifcool.finpro.core.helpers", "color": " #969696"
}
];
