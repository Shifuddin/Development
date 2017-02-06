from Tkinter import *
from ttk import *
from tkFileDialog import askopenfilename
from tkFileDialog import askdirectory
from tkFileDialog import asksaveasfile
import tkFileDialog
import subprocess
import sys
import tempfile
import tkMessageBox
import json
import os
from glob import glob
import Tkinter
from ScrolledText import *
import Tix as tk
import ttk
import xml.etree.cElementTree as ET
import tkHyperlinkManager
import functools
# if you are working under Python 3, comment the previous line and comment out the following line
#from tkinter import *

target_path = ''
config_path = ''
merged_list = []
total_list = []
group = 'none'
list_source = []
list_config = []
no_config = 1
r_config = 0
combi_v = []
combi_w = []
cheki_s = []
frami_a = []
lebi_a = []
total_config = []
init_height= 260
circular = 3
fileview_l = []
treeview_l = []

'''
Load json tables
'''
def loadtable():
        global sw
        sw.pack_forget()
        sw= tk.ScrolledWindow(tab3, scrollbar=tk.Y) # just the vertical scrollbar
        sw.pack(fill=tk.BOTH, expand=1)
        global sw1
        sw1.pack_forget()
        sw1= tk.ScrolledWindow(tab4, scrollbar=tk.Y) # just the vertical scrollbar
        sw1.pack(fill=tk.BOTH, expand=1)
	result = [x[0] for x in os.walk('TEMP')]
	result.pop(0)
	global treeview_l
	treeview_l = []
	kl = 0
	for a in result:
                
                        ttk.Label(sw.window, text=a, foreground = '#990000').pack()
                        ttk.Label(sw1.window, text=a, foreground = '#990000').pack()
                        files = [y for x in os.walk(a) for y in glob(os.path.join(x[0], '*'))]
                        createtablenew()
                        sw1.window.treeview.bind("<Double-1>", functools.partial(OnDoubleClick, param=kl))
			for b in files:
				with open(b) as data_file:    
					data = json.load(data_file)
				if "ToolsErrors" in b:
                                        toolname = ''
                                        for eachpara in data:
                                                i = 0
                                                for eachline in eachpara:
                                                        i += 1  
                                                       
                                                        if i == 2:
                                                                createtable('tools')
                                                                sw1.window.treeview.insert("", 7, eachpara[eachline], text="Tool - " +toolname)
                                                                j = 0
                                                                for eachvalue in eachpara[eachline]:
						
                                                                        filename = eachvalue['file']
                                                                        linenumber = eachvalue['line']
                                                                        if not filename and  not linenumber:
                                                                                filename = 'not found'
                                                                                linenumber = ''
                                                                        if not filename and linenumber:
                                                                                filename = 'not found'
                                                                                #t2.insert(END, 'Line:'+'- '+str(linenumber)+ "\n", 'big')
                                                                        if not linenumber and filename:
                                                                                linenumber = ''
                                                                        sw.window.treeview.insert('', 'end', text = filename, values=(str(linenumber), eachvalue['qualifier'], eachvalue['variable'], eachvalue['procedure'], eachvalue['type']))
                                                                        sw1.window.treeview.tag_configure('oddrow', background='#ffe6cc')
                                                                        if j % 2 == 0:
                                                                                sw1.window.treeview.insert(eachpara[eachline], 7, text=filename,values=(str(linenumber), eachvalue['qualifier'], eachvalue['variable'], eachvalue['procedure'], eachvalue['type'], '', ''), tags = ('oddrow'))
                                                                        else:
                                                                                sw1.window.treeview.insert(eachpara[eachline], 7, text=filename,values=(str(linenumber), eachvalue['qualifier'], eachvalue['variable'], eachvalue['procedure'], eachvalue['type'], '', ''))
                                                                                
                                                                        j += 1
						
                                                        else:
                                                                toolname = eachpara[eachline]
                                                                ttk.Label(sw.window, text="Tools Error, "+eachline+ "-"+eachpara[eachline], foreground = '#008000').pack(fill="x")
                                                                
                                        #ttk.Label(sw.window, text='   ', foreground = '#cc9990').pack()
					
				if "RefinedErrors1" in b:
                                        ttk.Label(sw.window, text="Refined Error", foreground = '#008000').pack(fill="x")
                                       
                                        createtable('refined')
                                        sw1.window.treeview.insert("", 7, 'dir2', text="Refined")
                                        j = 0
                                        #ttk.Label(sw.window, text='   ', foreground = '#cc9990').pack()
                                        for eachline in data:
                                                #t2.insert(END, a['type']+ ': '+ a['procedure']+"\n" , 'color')
                                                filename = eachline['file']
                                                linenumber = eachline['line']
                                                if not filename and  not linenumber:
                                                        filename = 'not found'
                                                        linenumber = ''
                                                if not filename and linenumber:
                                                        filename = 'not found'
                                                        #t2.insert(END, 'Line:'+'- '+str(linenumber)+ "\n", 'big')
                                                if not linenumber and filename:
                                                        linenumber = ''
                                                        #t2.insert(END, 'Line:'+'- '+"..."+filename +"\n", 'big')
                                                #t2.insert(END, a['qualifier']+ ', Variable: '+ a['variable']+"\n" , 'big')
                                                sw.window.treeview.insert('', 'end', text = filename, values=(str(linenumber), eachline['qualifier'], eachline['variable'], eachline['procedure'], eachline['type'], str(eachline['status']), eachline['severity']))
                                                sw1.window.treeview.tag_configure('oddrow', background='#ffe6cc')
                                                if j % 2:
                                                        sw1.window.treeview.insert('dir2', 7, text=filename,values=(str(linenumber), eachline['qualifier'], eachline['variable'], eachline['procedure'], eachline['type'], str(eachline['status']), eachline['severity']), tags=('oddrow'))
                                                else:
                                                        sw1.window.treeview.insert('dir2', 7, text=filename,values=(str(linenumber), eachline['qualifier'], eachline['variable'], eachline['procedure'], eachline['type'], str(eachline['status']), eachline['severity']))
                                                j += 1
					
				if "MergedErrors" in b:
                                        
                                        ttk.Label(sw.window, text="Merged Error", foreground = '#008000').pack(fill="x")
                                      
                                        createtable('merged')
                                        sw1.window.treeview.insert("", 7, 'dir1', text="Merged")
                                        
                                        #ttk.Label(sw.window, text='   ', foreground = '#cc9990').pack()
                                        j = 0
                                        for eachline in data:
                                                #t2.insert(END, a['type']+ ': '+ a['procedure']+"\n" , 'color')
                                                filename = eachline['file']
                                                linenumber = eachline['line']
                                                if not filename and  not linenumber:
                                                        filename = 'not found'
                                                        linenumber = ''
                                                if not filename and linenumber:
                                                        filename = 'not found'
                                                        #t2.insert(END, 'Line:'+'- '+str(linenumber)+ "\n", 'big')
                                                if not linenumber and filename:
                                                        linenumber = ''
                                                        #t2.insert(END, 'Line:'+'- '+"..."+filename +"\n", 'big')
                                                #t2.insert(END, a['qualifier']+ ', Variable: '+ a['variable']+"\n" , 'big')
                                                #filename = '<a href="http://www.example.com">' + filename + '</a>'
                                                sw.window.treeview.insert('', 'end', text = filename, values=(str(linenumber), eachline['qualifier'], eachline['variable'], eachline['procedure'], eachline['type']))
                                                sw1.window.treeview.tag_configure('oddrow', background='#ffe6cc')
                                                if j %2 == 0:
                                                        sw1.window.treeview.insert('dir1', 7, text=filename,values=(str(linenumber), eachline['qualifier'], eachline['variable'], eachline['procedure'], eachline['type'], '', ''), tags=('oddrow'))
                                                else:
                                                        sw1.window.treeview.insert('dir1', 7, text=filename,values=(str(linenumber), eachline['qualifier'], eachline['variable'], eachline['procedure'], eachline['type'], '', ''))
                                                j += 1
					
				
			
                        kl +=1
'''
when double clicked on item
'''
def OnDoubleClick(event, param):

       
        global fileview_l

        if len(fileview_l) > 0:
                fileview_l[0].destroy()
        item = treeview_l[param].selection()[0]
        filename = treeview_l[param].item(item,"text")
        linenumber = treeview_l[param].item(item,"values")
        fileview = Toplevel()
        fileview.title(target_path)
        fileview.geometry("700x560+450+450")
        fileview_l.append(fileview)

        
        filename_text = Text(fileview, state='normal', height = 2, width = 100, bg='#f5f5f0')
        filename_text.pack()
        filename_text.tag_configure('color3', background='#0077b3',foreground='#ffffff', font=('Verdana', 12, 'bold'), justify = CENTER)
        filename_text.tag_configure('color2', background='#808000',foreground='#ffffff', font=('Verdana', 9, 'bold'))
        filename_text.insert(END, filename +"\n", 'color3')

        if len(linenumber[1]) > 90:
                firstpart = linenumber[1][0:90]
                secondpart = '        - '+linenumber[1][90:len(linenumber[1]) -1]
        else:
                firstpart = linenumber[1]
                secondpart = ''
                
	filename_text.insert(END, "        Reason: "+firstpart + "\n", 'color2')
	filename_text.insert(END, secondpart, 'color2')	

        filename_text.configure(state = 'disabled')
        style_text = Text(fileview, state='normal', height = 35, width = 2, bg='#808080')
        style_text.pack(side = LEFT)
        #fileview_text.tag_configure('color1', background='#808080',foreground='#808080', font=('Verdana', 9, 'bold'))
        
        fileview_text = ScrolledText(fileview, state='normal', height = 35, width = 100, bg='#FFFCFC')
        fileview_text.pack(side = LEFT)
        
        
        fileview_text.tag_configure('big1', font=('Verdana', 11, 'bold'), justify = CENTER)
        fileview_text.tag_configure('big0', font=('Verdana', 10, 'bold'))
	fileview_text.tag_configure('big2', font=('Verdana', 9, 'bold'))
	fileview_text.tag_configure('big', font=('Verdana', 7))
	fileview_text.tag_configure('color', background='#b30000', foreground='#ffffff', font=('Verdana', 8, 'bold'))
        
        #fileview_text.add_separator()
        
	fileview_text.insert(END, "\n", 'color2')
	#fileview_text.insert(END, "\n", 'big0')
        i = 1
        with open(target_path, 'r') as f:
                for line in f:
                        if str(i) == linenumber[0]:
                                #fileview_text.insert(END, ".>     ", 'color1')
                                fileview_text.insert(END, str(i)+ "" + ".>     ", 'color')
                                fileview_text.insert(END, line , 'color')
                        else:
                                #fileview_text.insert(END, ".>     ", 'color1')
                                fileview_text.insert(END, str(i)+ ""+ ".>     ", 'big')
                                fileview_text.insert(END, line, 'big')
                        #style_text.insert(END, "  "+ str(i)+".>     ", 'big')
                                
                        i +=1
        f.close()
	#fileview_text.insert(END, ".>     ", 'color1')
	# create a popup menu
        popmenu = Menu(fileview)
        popmenu.add_command(label="Undo", command=save)
        popmenu.add_command(label="Redo", command=save)
        
        # attach popup to frame
        fileview_text.bind("<Button-3>", functools.partial(popup, param=popmenu))
        fileview_text.configure(state = 'disabled')

'''
Save result
'''        
def save():
        print "Save"

'''
Context popup
'''
def popup(event, param):
        param.post(event.x_root, event.y_root)

'''
Create result table
'''
def createtable(tabletype):
	tv = Treeview(sw.window)
	if (tabletype == 'refined'):
                tv['columns'] = ('line', 'qualifier', 'variable', 'procedure', 'type', 'status', 'severity')
        else:
                tv['columns'] = ('line', 'qualifier', 'variable', 'procedure', 'type')
	tv.heading("#0", text='File')
	tv.column("#0", anchor="center", width = 75)
	tv.heading('line', text='Line')
	tv.column('line', anchor='center', width=75)
	tv.heading('qualifier', text='Qualifier')
	tv.column('qualifier', anchor='center', width=100)
	tv.heading('variable', text='Variable')
	tv.column('variable', anchor='center', width=75)
	tv.heading('procedure', text='Procedure')
	tv.column('procedure', anchor='center', width=100)
	tv.heading('type', text='Type')
	tv.column('type', anchor='center', width=100)
	if (tabletype == 'refined'):
                tv.heading('status', text='Status')
                tv.column('status', anchor='center', width=50)
                tv.heading('severity', text='Severity')
                tv.column('severity', anchor='center', width=100)
	tv.pack(fill="x")
	sw.window.treeview = tv
	sw.window.grid_rowconfigure(0, weight = 2)
	sw.window.grid_columnconfigure(0, weight = 2)
'''
Create result table with different style
'''
def createtablenew():
        global tree
        global treeview_l
        tree = ttk.Treeview(sw1.window)
        
        tree["columns"]=("one","two", "three", "four", "five", "six", "seven")
        tree.column("one", width=50)
        tree.column("two", width=100)
        tree.column("three", width=100)
        tree.column("four", width=100)
        tree.column("five", width=100)
        tree.column("six", width=40)
        tree.column("seven", width=70)
        tree.heading("one", text="Line")
        tree.heading("two", text="Procedure")
        tree.heading("three", text="Variable")
        tree.heading("four", text="Type")
        tree.heading("five", text="Qualifier")
        tree.heading("six", text="Status")
        tree.heading("seven", text="Severity")
        tree.pack()
        sw1.window.treeview = tree
        treeview_l.append(sw1.window.treeview)
'''
Select target file
'''
def NewTargetFile(e1):
	name = askopenfilename()
	t1.config (state = 'normal')
	t1.delete("1.0", "end")
	if len(config_path) > 0 and len(name) > 0:
		t1.insert(END, 'Target: ' + name + '\n', 'big')
		t1.insert(END, 'Config: ' + config_path + '\n', 'big')
	if len(config_path) <= 0 and len(name) > 0:
		t1.insert(END, 'Target: ' + name + '\n', 'big')


        if len(name) > 0:
                
                print "Old Length of List Source " + str(len(list_source))
                if name in list_source:
                        print "Already " + name +"exists"
                        e1.set(name)
                else:
                        list_source.append(name)
                        e1['values'] = list_source
                        e1.current(len(list_source)-1)
                print "New Length of List Source " + str(len(list_source))
                print "Current Elements of List Source"
                for source in list_source:
                        print source
                
	note.select(tab1)
	t1.config (state = 'disabled')
	global target_path;
	target_path = name;
'''
Select config file
'''
def NewConfigFile(e2):
	name = askopenfilename()
	t1.config (state = 'normal')
	t1.delete("1.0", "end")
	if len(target_path) > 0 and len(name) > 0:
		t1.insert(END, 'Target: ' + target_path + '\n', 'big')
		t1.insert(END, 'Config: ' + name + '\n', 'big')
	if len(target_path) <= 0 and len(name) > 0:
		t1.insert(END, 'Config: ' + name + '\n', 'big')

        if len(name) > 0:
                print "Old Length of List Config " + str(len(list_config))
                if name in list_config:
                        print "Already " + name +"exists"
                        e2.set(name)
                else:
                        list_config.append(name)
                        e2['values'] = list_config
                        e2.current(len(list_config)-1)
                print "New Length of List Config " + str(len(list_config))
                print "Current Elements of List config"
                for config in list_config:
                        print config
        
	note.select(tab1)
	t1.config (state = 'disabled')
	global config_path
	config_path = name
'''
Select sources
'''
def ListSources(e1):
        global list_source
        list_source = []
        if os.path.exists('recentlog.xml'):
                print "Source Files Exist. They Are..."
                tree = ET.parse('recentlog.xml')
                xmlroot = tree.getroot()

                for elementlist in xmlroot:
                        if elementlist.get('name') == "source":
                                for element in elementlist:
                                        list_source.append(element.text)
                                        print element.text
                e1['values'] = list_source

                if len(list_source) > 0:
                        e1.current(0)
'''
List all configs
'''
def ListConfigs(e2):
        global list_config
        list_config = []
        if os.path.exists('recentlog.xml'):
                print "Config Files Exist. They Are... "
                tree = ET.parse('recentlog.xml')
                xmlroot = tree.getroot()

                for elementlist in xmlroot:
                        if elementlist.get('name') == "config":
                                for element in elementlist:
                                        list_config.append(element.text)
                                        print element.text
                e2['values'] = list_config

                if len(list_config) > 0:
                        e2.current(0)
'''
Load all recent files
'''
def LoadRecentFiles(e1):
        print "Loading Recent Files"
        ListSources(e1)
        #ListConfigs(e2)
        print "Loading Completed"

'''
Read configurations
'''                               
def ReadConfig():
        iternumber = no_config/3
        print "Length " +str(len(cheki_s))

        j = 0
        k = 0
        l = 0
        str_conf_line = ''
        for i in range (iternumber):
                if i == 0:
                        j = l
                        k = l+1
                        l = l +2
                else:
                        j = l +1
                        k = l + 2
                        l = l + 3
                global m
                m = 0
                str_conf_line = '-sa "'

                if cheki_s[j].get() == 1:
                        
                        str_conf_line = str_conf_line+ 'cppcheck'
                        m += 1
                if cheki_s[k].get() == 1:
                        if m > 0:
                                str_conf_line = str_conf_line + ' infer'
                        else:
                                str_conf_line = str_conf_line + 'infer'
                        m += 1
                if cheki_s[l].get() == 1:
                        if m > 0:
                                str_conf_line = str_conf_line + ' coverity'
                        else:
                                str_conf_line = str_conf_line + 'coverity' 
                str_conf_line = str_conf_line + '"'
                if str(combi_v[i].get()) != 'none':
                        str_conf_line = str_conf_line + ' -v '
                        str_conf_line = str_conf_line + '"'+combi_v[i].get()+'"'
                if str(combi_w[i].get()) != 'none':
                        str_conf_line = str_conf_line + ' -wp '
                        str_conf_line = str_conf_line + '"'+combi_w[i].get()+'"'


                total_config.append(str_conf_line)
        target = open("config.txt", 'w')
        for str_conf in total_config:
                        #print str_conf
                target.write(str_conf + '\n')
        target.close() 

'''
Save recent files
'''             
def SaveRecentFiles(e1, top):
        print "Number of config" + str(no_config/3)
        ReadConfig()
        global target_path
        global config_path
        target_path = e1.get()
        config_path = 'config.txt'
        print "Current selected source "+target_path
        print "Current selected config "+config_path
        if len(target_path) > 0 and len(config_path) > 0:
                t1.config(state = 'normal')
                t1.delete("1.0", "end")
                t1.insert(END, 'Target: ' + target_path + '\n', 'big')
		t1.insert(END, 'Config: ' + config_path + '\n', 'big')
		t1.config (state = 'disabled')
	if len(target_path) <= 0:
                t1.config(state = 'normal')
                t1.insert(END, 'Target path missing ' + '\n', 'big')
                t1.config (state = 'disabled')
        if len(config_path) <= 0:
                t1.config(state = 'normal')
                t1.insert(END, 'Config path missing ' + '\n', 'big')
                t1.config (state = 'disabled')

        print "Currently we have " +str(len(list_source)) + " Sources. They are..."
        for source in list_source:
                print source
        
        if os.path.exists('recentlog.xml'):
                print "Log File Exists"
                tree = ET.parse('recentlog.xml')
                xmlroot = tree.getroot()
                source_found = 0
                config_found = 0
                new_list_source = []
                new_list_config = []
                for elementlist in xmlroot:
                        if elementlist.get('name') == "source":
                                for source in list_source:
                                        source_found = 0        
                                        for element in elementlist:
                                                if source == element.text:
                                                        source_found = 1
                                                        break
                                        if source_found == 0:
                                                new_list_source.append(source)
                                for new_source in new_list_source:
                                        ET.SubElement(elementlist, "field").text = new_source
                                        tree = ET.ElementTree(xmlroot)
                                tree.write('recentlog.xml')
                        '''
                        if elementlist.get('name') == "config":
                                for config in list_config:
                                        config_found = 0        
                                        for element in elementlist:
                                                if config == element.text:
                                                        config_found = 1
                                                        break
                                        if config_found == 0:
                                                new_list_config.append(config)
                                for new_config in new_list_config:
                                        ET.SubElement(elementlist, "field").text = new_config
                                        tree = ET.ElementTree(xmlroot)
                                tree.write('recentlog.xml')
                        '''
                print "Length of new list " + str(len(new_list_source))
                print "Length of new list " + str(len(new_list_config))
                 
        else:
                print "No file"
                xmlroot = ET.Element("root")
                doc_source = ET.SubElement(xmlroot, "doc", name = "source")
                #doc_config = ET.SubElement(xmlroot, "doc1", name = "config")

                for element in list_source:
                        ET.SubElement(doc_source, "field").text = element

                '''
                for element in list_config:
                        ET.SubElement(doc_config, "field").text = element
                '''

                tree = ET.ElementTree(xmlroot)
                tree.write("recentlog.xml")
        
        top.destroy()
def CreateConfig(sw_config, top):
        global init_height
        global circular
        
        top.geometry("500x"+str(init_height)+"+1+1")
        global no_config
        global r_config
        '''
        if no_config > 15:
                return
        '''
        label0 = ttk.Label(sw_config, text="Configuration " + str(no_config - r_config))
        lebi_a.append(label0)
        
        
        label0.grid(row = no_config-1, column = 0)
        fm1 = Frame(sw_config)
        
        label1 = ttk.Label(fm1, text="Static Analysis").pack(side = TOP)
        measureSystem1 = IntVar()
        measureSystem2 = IntVar()
        measureSystem3 = IntVar()
        check1 = ttk.Checkbutton(fm1, text='Cpp Check', variable=measureSystem1, onvalue=1, offvalue=0).pack(side = LEFT)
        check2 = ttk.Checkbutton(fm1, text='Infer', variable=measureSystem2, onvalue=1, offvalue=0).pack(side = LEFT)
        check3 = ttk.Checkbutton(fm1, text='Coverity', variable=measureSystem3, onvalue=1, offvalue=0).pack(side = LEFT)
        cheki_s.append(measureSystem1)
        cheki_s.append(measureSystem2)
        cheki_s.append(measureSystem3)
        fm1 .grid(row = no_config, column = 1)

        frami_a.append(fm1)
        fm20 = Frame(sw_config)
        label1 = ttk.Label(fm20, text="").pack(side = TOP)
        
        fm20.grid(row = no_config, column = 3)
        frami_a.append(fm20)        

        fm2 = Frame(sw_config)
        label1 = ttk.Label(fm2, text="Verification").pack(side = TOP)
        e3 = ttk.Combobox(fm2, width = "10")
        e3.pack()
        e3['values'] = ['cbmc', 'spin', 'none']
        e3.current(0)
        combi_v.append(e3)
        fm2 .grid(row = no_config, column = 5)
        frami_a.append(fm2)
        
        fm40 = Frame(sw_config)
        label1 = ttk.Label(fm40, text="").pack(side = TOP)
        fm40 .grid(row = no_config +1, column = 7)
        frami_a.append(fm40)

        
        fm3 = Frame(sw_config)
        label1 = ttk.Label(fm3, text="WP").pack(side = TOP)
        e4 = ttk.Combobox(fm3, width = "10")
        e4.pack()
        e4['values'] = ['frama-c', 'none']
        e4.current(0)
        combi_w.append(e4)
        fm3 .grid(row = no_config, column = 9)
        frami_a.append(fm3)
        
        fm4 = Frame(sw_config)
        label1 = ttk.Label(fm4, text="").pack(side = TOP)
        fm4 .grid(row = no_config +1, column = 0)
        frami_a.append(fm4)
        
        no_config += 3
        r_config +=2
        circular += 1
        init_height = init_height + 70 + circular
        
def RemoveLastConfig(top):
        print len(frami_a)
        global no_config
        global combi_v
        global combi_w
        global r_config
        global init_height
        
        if len(lebi_a) <=1:
                tkMessageBox.showinfo("Warning", "Can't Delete Only Configuration")
                return
                
        no_config -=3
        r_config -=2
        init_height = init_height - 70 - circular
        top.geometry("500x"+str(init_height)+"+1+1")
        i = 0
        for frami in reversed(frami_a):
                frami.grid_remove()
                frami_a.pop()
                if i == 5:
                        break
                i +=1
        for lebi in reversed(lebi_a):
                lebi.grid_remove()
                lebi_a.pop()
                break
        
        combi_v.pop()
        combi_w.pop()
         
        i = 0

        for checki in cheki_s:
                cheki_s.pop()
               
                if i ==2:
                        break
                i +=1
        
                
'''
Invoked when run is clicked
'''
def NewRun():
        global combi_v
        global combi_w
        global cheki_s
        global frami_a
        global lebi_a
        global no_config
        global r_config
        global total_config
        global init_height
        global circular
        total_config = []
        no_config = 1
        r_config = 0
        combi_v = []
        combi_w = []
        cheki_s = []
        frami_a = []
        lebi_a = []
        init_height = 270
        circular = 3
        top = Toplevel()
        top.title("Select Files..")
        top.geometry("500x260+450+450")
        top.transient(root)
        label1 = ttk.Label(top, text="Choose Configuration").pack()
        sw_config= tk.ScrolledWindow(top, scrollbar=tk.Y) # just the vertical scrollbar
        sw_config.pack(fill=tk.BOTH, expand=1)


       

        CreateConfig(sw_config, top)



        button0 = ttk.Button(top, text="Add More Configuration", command = lambda: CreateConfig(sw_config, top))
        button0.pack()
        button1 = ttk.Button(top, text="Delete Configuration", command = lambda: RemoveLastConfig(top))
        button1.pack()
        label40 = ttk.Label(top, text="").pack(side = LEFT)
        label1 = ttk.Label(top, text="Choose Source File").pack()
        e1 = ttk.Combobox(top, width = "10")
        e1.pack(fill = "x")
        button = ttk.Button(top, text="Browse", command = lambda: NewTargetFile(e1))
        button.pack()
        label2 = ttk.Label(top, text="").pack(fill = "x")
      
        button2 = ttk.Button(top, text="Done", command = lambda: SaveRecentFiles(e1, top))
        button2.pack()
        
        LoadRecentFiles(e1)
        print "New Run"
'''
Selects target directory
'''   
def NewTargetDirectory():
        #name = askdirectory()
        #address.insert(END, name)
        filez = tkFileDialog.askopenfilenames(parent=root,title='Choose a file')
        filez = root.tk.splitlist(filez)
	print "New Target Directory"

'''
Save result in disk
'''
def SaveResultAsText():
	if len (str(t2.get(1.0, END))) > 1:
		f = asksaveasfile(mode='w', defaultextension=".txt", initialfile = "Errorlist", title = "Save Result as Text")
		if f is None:
			return
		text2save = str(t2.get(1.0, END)) # starts from `1.0`, not `0.0`
		f.write(text2save)
		f.close()
	else:
		tkMessageBox.showinfo("Warning", "No result available")
		
	print "SaveResultAsText"
'''
Export result to xml
'''
def ExportResultAsXml():
	if len(merged_list) > 0:
		f = asksaveasfile(mode='w', defaultextension=".xml", initialfile = "Errorlist", title = "Save Result as xml")
		if f is None:
			return
		f.write("<autoanalyzer>\n")
		j = 0
		i = 0
		for a in merged_list:
			for b in a:
				if b == "Group" or b == "ErrorType":
					if j == 0:
						f.write("<"+b+" value ="+"\""+str(a[b])+"\">"+"\n")
					else:
						if b == "Group":
							f.write("</ErrorType>\n")
							f.write("</Group>\n")
							f.write("<"+b+" value ="+"\""+str(a[b])+"\">"+"\n")
							i = 0
						else:
							if i > 0:
								f.write("</ErrorType>\n")
							f.write("<"+b+" value ="+"\""+str(a[b])+"\">"+"\n")
							i += 1
					j += 1
				else:
					f.write("<"+b+">"+str(a[b])+"</"+b+">"+"\n")
					
		f.write("</ErrorType>\n")
		f.write("</Group>\n")
		f.write("</autoanalyzer>")
		f.close()
	else:
		tkMessageBox.showinfo("Warning", "No result available")
	print "Export Result as Xml"
'''
Export result to csv
'''
def ExportResultAsCsv():
	if len(merged_list) > 0:
		f = asksaveasfile(mode='w', defaultextension=".csv", initialfile = "Errorlist", title = "Save Result as Csv")
		if f is None:
			return
		for a in merged_list:
			for b in a:
				f.write(b+","+str(a[b])+ "\n")
		f.close()
	else:
		tkMessageBox.showinfo("Warning", "No result available")
	print "ExportResultAsCsv"
	
def ExportResultAsPdf():
	print "ExportResultAsPdf"
def Exit():
	root.destroy()

def Copy():
	print "Copy"
def Find():
	print "Find"
def SelectAll():
	print "Select All"
	
def ViewByFile():
        print "View By File"
def ViewByConfiguration():
        print "View By Configuration"

'''
find the group of the file like merged, refined etc
'''
def findgroup(filepath):
	
	lastslash = filepath.rfind('/')
	firstslash = filepath.find('/')
	return filepath[firstslash+1: lastslash]
'''
Invoked when scan is clicked
'''
def FullScan():
        print target_path + "  add " + config_path
        if (len(target_path) > 0 and len(config_path) > 0):
                        treeview_l = []
                        
			t4.config(state = 'normal')
			t4.delete("1.0", "end")
			cmd = "java engine -src " +target_path+ " -wf \"CodeReview\" -conf "+config_path
			p = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, stdin=subprocess.PIPE)
			while True:
				out = p.stdout.read()
				err = p.stderr.read()
				t4.insert(END, out + '\n' + err)
				if out == '' and p.poll() != None:
					break		
			t4.config(state = 'disabled')
			
			t2.config (state = 'normal')
			t2.tag_configure('big1', font=('Verdana', 11, 'bold'))
			t2.tag_configure('big2', font=('Verdana', 9, 'bold'))
			t2.tag_configure('big', font=('Verdana', 7))
			t2.tag_configure('color', foreground='#cc9900', 
						font=('Tempus Sans ITC', 8, 'bold'))
			scroll = Scrollbar(tab2, command=t2.yview)
			t2.configure(yscrollcommand=scroll.set)
			t2.delete("1.0", "end")
			result = [y for x in os.walk('TEMP') for y in glob(os.path.join(x[0], '*'))]
			global group
			for a in result:
				if 'MergedErrors' in a:
					group1 = findgroup(a)
					if group != group1:
						group = group1
						dic = {"Group": group}
						merged_list.append(dic)
						t2.insert(END, group + "..." + "\n", 'big1')
					MergedError(a)  
				if 'RefinedErrors1' in a:
					group1 = findgroup(a)
					if group != group1:
						group = group1
						dic = {"Group": group}
						merged_list.append(dic)
						t2.insert(END, group  + "..."+"\n", 'big1')
					RefinedError(a)
				if 'ToolsErrors' in a:
					group1 = findgroup(a)
					if group != group1:
						group = group1
						dic = {"Group": group}
						merged_list.append(dic)
						t2.insert(END, group + "..."+ "\n", 'big1')
					ToolsError(a)
			t2.config(state = 'disabled')
			loadtable()
			note.select(tab2)
			print "Full Scan"
	else:
			tkMessageBox.showinfo("Waring", "Select Both Target and Config File")
def ScanByInfer():
        print "Scan by Infer"
def ScanByCppCheck():
        print "Scan by CppCheck"
def ScanByCoverityCheck():
        print "Scan by CoverityCheck"

def SettingsCnCplus():
        print "Scan C/C++ files"
def SettingsJava():
        print "Scan Java files"
def SettingsObjectiveC():
        print "Scan Objective C"
def SettingsAllLanguage():
        print "Scan All language"
def About():
	tkMessageBox.showinfo("Autoanalyzer", "Integration tool for static analysis")
'''
Create report from merge json file
'''
def MergedError(filename):
	t2.insert(END, "Merged Error"+"\n", 'big2')
	dic = {"ErrorType": "Merged Error"}
	
	with open(filename) as data_file:    
		global merged_list
		data_merged = json.load(data_file)
		merged_list.append(dic)
		merged_list = merged_list + data_merged
		for a in data_merged:
			t2.insert(END, a['type']+ ': '+ a['procedure']+"\n" , 'color')
			filename = a['file']
			linenumber = a['line']
			if filename and  linenumber:
				t2.insert(END, 'Line:'+'- '+str(linenumber)+ " "+filename+"\n", 'big')
			if not filename and linenumber:
				t2.insert(END, 'Line:'+'- '+str(linenumber)+ "\n", 'big')
			if not linenumber and filename:
				t2.insert(END, 'Line:'+'- '+"..."+filename +"\n", 'big')
			t2.insert(END, a['qualifier']+ ', Variable: '+ a['variable']+"\n" , 'big')
			t2.insert(END, '\n', 'big')
'''
Create report from refine json file
'''		
def RefinedError(filename):
	t2.insert(END, "Refined Error"+"\n", 'big2')
	dic = {"ErrorType": "Refined Error"}
	with open(filename) as data_file:
		global merged_list
		data_refined = json.load(data_file)
		merged_list.append(dic)
		merged_list = merged_list + data_refined
		for a in data_refined:
			t2.insert(END, a['severity']+ ': '+a['type']+ ': '+ a['procedure']+', Status: '+ str(a['status'])+"\n" , 'color')
			filename = a['file']
			linenumber = a['line']
			if filename and  linenumber:
				t2.insert(END, 'Line:'+'- '+str(linenumber)+ " "+filename+"\n", 'big')
			if not filename and linenumber:
				t2.insert(END, 'Line:'+'- '+str(linenumber)+ "\n", 'big')
			if not linenumber and filename:
				t2.insert(END, 'Line:'+'- '+"..."+filename +"\n", 'big')
			t2.insert(END, a['qualifier']+ ', Variable: '+ a['variable']+"\n" , 'big')
			t2.insert(END, '\n', 'big')
'''
Create report from toolserror json file
'''			
def ToolsError(filename):
	dic = {"ErrorType": "Tools Error"}
	with open (filename) as data_file:
		global merged_list
		data_tools = json.load(data_file)
		merged_list.append(dic)
		merged_list = merged_list + data_tools
		for a in data_tools:
			i = 0
			for b in a:
				i += 1
				if i == 2:
					for c in a[b]:
						
						t2.insert(END, c['type']+ ': '+ c['procedure']+"\n" , 'color')
						filename = c['file']
						linenumber = c['line']
						if filename and  linenumber:
							t2.insert(END, 'Line:'+'- '+str(linenumber)+ " "+filename+"\n", 'big')
						if not filename and linenumber:
							t2.insert(END, 'Line:'+'- '+str(linenumber)+ "\n", 'big')
						if not linenumber and filename:
							t2.insert(END, 'Line:'+'- '+"..."+filename +"\n", 'big')
						t2.insert(END, c['qualifier']+ ', Variable: '+ c['variable']+"\n" , 'big')
						t2.insert(END, '\n', 'big')
						
				else:
					t2.insert(END, "Tools Error, " + b+ "-"+a[b] + '\n', 'big2')
#initialize root
root = tk.Tk()
root.title("Autoanalyzer")
menu = Menu (root)
root.config(menu = menu)
address = Text(root, height = 1.4, width = root.winfo_screenwidth()/3)
address.config(state = 'disabled')
address.pack()
#add menu
filemenu = Menu (menu)
menu.add_cascade (label = "File", menu = filemenu)
filemenu.add_command (label = "New Run", command = NewRun)
filemenu.add_separator()

filemenu.add_command (label = "Save Result As Text", command = SaveResultAsText)
filemenu.add_command (label = "Export Result as Xml", command = ExportResultAsXml)
filemenu.add_command (label = "Export Result As Csv", command = ExportResultAsCsv)
filemenu.add_command (label = "Export Result As Pdf", command = ExportResultAsPdf)
filemenu.add_separator()
filemenu.add_command (label = "Exit", command = Exit)


#edit menu
editmenu = Menu (menu)
menu.add_cascade (label = "Edit", menu = editmenu)
editmenu.add_command(label ="Copy", command = Copy)
editmenu.add_command(label ="Find", command = Find)
editmenu.add_separator()
editmenu.add_command(label ="Select All", command = SelectAll)

#view menu
viewmenu = Menu (menu)
menu.add_cascade (label = "View", menu = viewmenu)
viewmenu.add_command(label ="View Result by File", command = ViewByFile)
viewmenu.add_command(label ="View Result by Configuration", command = ViewByConfiguration)

#analysis menu
scanmenu = Menu (menu)
menu.add_cascade (label = "Analyse", menu = scanmenu)
scanmenu.add_command(label ="Full Analyse", command = FullScan)
scanmenu.add_separator()
scanmenu.add_command(label ="Analyse by Infer", command = ScanByInfer)
scanmenu.add_command(label ="Analyse by Cppcheck", command = ScanByCppCheck)
scanmenu.add_command(label ="Analyse by CoverityCheck", command = ScanByCoverityCheck)

#settings menu
settingsmenu = Menu (menu)
menu.add_cascade (label = "Settings", menu = settingsmenu)
settingsmenu.add_checkbutton(label="C/C++", onvalue=True, offvalue=False, command=SettingsCnCplus)
settingsmenu.add_checkbutton(label="Java", onvalue=True, offvalue=False, command=SettingsJava)
settingsmenu.add_checkbutton(label="Objective C", onvalue=True, offvalue=False, command=SettingsObjectiveC)
settingsmenu.add_separator()
settingsmenu.add_checkbutton(label="All Language", onvalue=True, offvalue=False, command=SettingsAllLanguage)

#help menu
help_menu = Menu(menu)
menu.add_cascade(label = "Help", menu = help_menu)
help_menu.add_command(label = "About Autoanalyzer", command = About)
#Create tab
note = Notebook(root)
tab1 = Frame(note)
tab2 = Frame(note)
tab3 = Frame(note)
tab4 = Frame(note)
tab5 = Frame(note)

#Button(tab1, text='Exit', command=root.destroy).pack(padx=100, pady=100)
t1 = ScrolledText(tab1, state='disabled', height = 35, width = 100)
t1.pack()
t2 = ScrolledText(tab2, state='disabled', height = 35, width = 100)
t2.pack()

t4 = ScrolledText(tab5, state='disabled', height = 35, width = 100)
t4.pack()
note.add(tab1, text = "Target Files", compound=TOP)
note.add(tab2, text = "Results")
note.add(tab3, text = "Summary Table")

note.add(tab4, text = "Summary Table Expansible")
note.add(tab5, text = "Console Output")
note.pack()

sw= tk.ScrolledWindow(tab3, scrollbar=tk.Y) # just the vertical scrollbar
sw.pack(fill=tk.BOTH, expand=1)

sw1= tk.ScrolledWindow(tab4, scrollbar=tk.Y) # just the vertical scrollbar
sw1.pack(fill=tk.BOTH, expand=1)

width = root.winfo_screenwidth()
height = root.winfo_screenheight()
root.geometry(str((width/2)) + "x" + str(height/2))
root.resizable(width=False, height=False)

root.mainloop()
