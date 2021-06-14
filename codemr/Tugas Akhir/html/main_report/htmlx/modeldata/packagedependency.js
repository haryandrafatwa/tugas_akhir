var matrix = [[0,0,0,0,0,0,1,0,0,1,0,0,0,3,0,0,0,0,0,1,1,2,0,0,0,0,0,0,1,3,0,0,0,0,0,3],[1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11,0,0,0,0,0,1],[0,0,0,0,0,0,0,6,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,2,0,0,0,0,0,1,1,0,0,0,0,3],[1,0,0,2,0,0,2,0,0,0,0,0,0,4,0,0,0,0,0,0,2,0,0,0,0,1,0,0,0,4,0,0,0,0,0,4],[0,0,0,3,0,0,0,0,0,0,0,0,4,7,0,0,0,0,0,1,3,2,0,0,0,0,0,0,0,8,1,0,0,0,0,3],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,13,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,2],[0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,0,0,0,0,2],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0],[0,0,0,0,0,0,10,0,0,0,0,0,15,0,5,0,0,0,0,0,5,14,0,0,0,0,0,0,0,3,15,0,5,0,0,15],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,5,0,0,0,0,5],[0,0,0,0,0,0,0,0,0,0,0,0,4,5,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,4,1,0,0,0,0,4],[0,0,0,0,0,0,0,0,0,3,0,0,1,3,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,1,1,0,0,0,0,3],[0,0,0,7,0,0,0,4,0,0,0,0,0,9,1,0,0,0,0,0,4,6,0,10,0,0,0,0,0,5,1,0,0,0,0,4],[0,0,1,0,0,0,0,0,0,0,0,0,4,5,0,1,0,0,0,1,1,3,0,0,0,0,0,0,0,7,1,0,0,0,0,5],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,4,0,0,0,3,0,0,0,0,6,6,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,3,0,0,0,0,0,3],[0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11,4,0,0,0,0,0],[0,0,4,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,4],[0,0,1,0,0,0,0,0,2,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1],[0,0,0,1,0,0,0,1,0,0,0,0,6,7,0,0,0,0,0,0,1,2,0,1,0,0,0,0,0,6,1,0,0,0,0,3],[0,0,5,0,0,0,0,0,0,0,0,0,3,10,0,1,0,0,0,3,0,10,0,0,0,0,0,0,0,6,1,0,0,0,0,8],[0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,1,0,0,0,0,3],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,3,0,0,0,0,0,5],[0,0,0,0,0,0,0,0,0,1,0,0,1,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,6,0,0,0,0,0,3],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0]]
var packages = [{
"name": " org.d3ifcool.finpro.mahasiswa.fragments.parent", "color": " #3182bd"
}
,{
"name": " org.d3ifcool.finpro.adapters", "color": " #6baed6"
}
,{
"name": " org.d3ifcool.finpro.dosen.adapters.recyclerview", "color": " #9ecae1"
}
,{
"name": " org.d3ifcool.finpro.core.mediators.prodi", "color": " #c6dbef"
}
,{
"name": " org.d3ifcool.finpro.activities", "color": " #e6550d"
}
,{
"name": " org.d3ifcool.finpro.prodi.activities.detail", "color": " #fd8d3c"
}
,{
"name": " org.d3ifcool.finpro.core.interfaces.objects", "color": " #fdae6b"
}
,{
"name": " org.d3ifcool.finpro.core.mediators.interfaces.prodi", "color": " #fdd0a2"
}
,{
"name": " org.d3ifcool.finpro.dosen.adapters.viewpager", "color": " #31a354"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.adapters.recyclerview", "color": " #74c476"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.fragments.child", "color": " #a1d99b"
}
,{
"name": " org.d3ifcool.finpro.prodi.activities", "color": " #c7e9c0"
}
,{
"name": " org.d3ifcool.finpro.core.interfaces.works", "color": " #756bb1"
}
,{
"name": " org.d3ifcool.finpro.core.presenters", "color": " #9e9ac8"
}
,{
"name": " org.d3ifcool.finpro.core.models.manager", "color": " #bcbddc"
}
,{
"name": " org.d3ifcool.finpro.dosen.activities.editor.update", "color": " #dadaeb"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.activities", "color": " #636363"
}
,{
"name": " org.d3ifcool.finpro.prodi.fragments", "color": " #969696"
}
,{
"name": " org.d3ifcool.finpro.dosen.activities.detail", "color": " #bdbdbd"
}
,{
"name": " org.d3ifcool.finpro.core.adapters", "color": " #d9d9d9"
}
,{
"name": " org.d3ifcool.finpro.core.interfaces", "color": " #3182bd"
}
,{
"name": " org.d3ifcool.finpro.core.interfaces.lists", "color": " #6baed6"
}
,{
"name": " org.d3ifcool.finpro.prodi.activities.editor.create", "color": " #9ecae1"
}
,{
"name": " org.d3ifcool.finpro.prodi.adapters", "color": " #c6dbef"
}
,{
"name": " org.d3ifcool.finpro.dosen.fragments.child", "color": " #e6550d"
}
,{
"name": " org.d3ifcool.finpro.dosen.fragments.parent", "color": " #fd8d3c"
}
,{
"name": " org.d3ifcool.finpro.prodi.activities.editor.update", "color": " #fdae6b"
}
,{
"name": " org.d3ifcool.finpro.dosen.activities", "color": " #fdd0a2"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.adapters.viewpager", "color": " #31a354"
}
,{
"name": " org.d3ifcool.finpro.core.models", "color": " #74c476"
}
,{
"name": " org.d3ifcool.finpro.core.api", "color": " #a1d99b"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.activities.editor", "color": " #c7e9c0"
}
,{
"name": " org.d3ifcool.finpro", "color": " #756bb1"
}
,{
"name": " org.d3ifcool.finpro.dosen.activities.editor.create", "color": " #9e9ac8"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.activities.detail", "color": " #bcbddc"
}
,{
"name": " org.d3ifcool.finpro.core.helpers", "color": " #dadaeb"
}
];
