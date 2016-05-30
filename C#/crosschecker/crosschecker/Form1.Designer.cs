namespace crosschecker
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.loadToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.loadDirectoryToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.openFileDialog1 = new System.Windows.Forms.OpenFileDialog();
            this.groupBox_port_prPorts = new System.Windows.Forms.GroupBox();
            this.listBox_port_prPorts = new System.Windows.Forms.ListBox();
            this.label2 = new System.Windows.Forms.Label();
            this.groupBox2_port_commonPorts = new System.Windows.Forms.GroupBox();
            this.label3 = new System.Windows.Forms.Label();
            this.listBox_port_commonPorts = new System.Windows.Forms.ListBox();
            this.groupBox_signal_srPorts = new System.Windows.Forms.GroupBox();
            this.label6 = new System.Windows.Forms.Label();
            this.listBox_signal_srPorts = new System.Windows.Forms.ListBox();
            this.groupBox_signal_dataElement = new System.Windows.Forms.GroupBox();
            this.label7 = new System.Windows.Forms.Label();
            this.listBox_signal_dataElement = new System.Windows.Forms.ListBox();
            this.label1 = new System.Windows.Forms.Label();
            this.groupBox_port_component = new System.Windows.Forms.GroupBox();
            this.listBox_port_component = new System.Windows.Forms.ListBox();
            this.port = new System.Windows.Forms.TabControl();
            this.tabPagePort = new System.Windows.Forms.TabPage();
            this.groupBox_port_shared_component = new System.Windows.Forms.GroupBox();
            this.label4 = new System.Windows.Forms.Label();
            this.listBox_port_shared_component = new System.Windows.Forms.ListBox();
            this.tabPageSignal = new System.Windows.Forms.TabPage();
            this.groupBox_signal_signal = new System.Windows.Forms.GroupBox();
            this.listBox_signal_signal = new System.Windows.Forms.ListBox();
            this.groupBox_signal_component = new System.Windows.Forms.GroupBox();
            this.label5 = new System.Windows.Forms.Label();
            this.listBox_signal_component = new System.Windows.Forms.ListBox();
            this.menuStrip1.SuspendLayout();
            this.groupBox_port_prPorts.SuspendLayout();
            this.groupBox2_port_commonPorts.SuspendLayout();
            this.groupBox_signal_srPorts.SuspendLayout();
            this.groupBox_signal_dataElement.SuspendLayout();
            this.groupBox_port_component.SuspendLayout();
            this.port.SuspendLayout();
            this.tabPagePort.SuspendLayout();
            this.groupBox_port_shared_component.SuspendLayout();
            this.tabPageSignal.SuspendLayout();
            this.groupBox_signal_signal.SuspendLayout();
            this.groupBox_signal_component.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.loadToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(1321, 28);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // loadToolStripMenuItem
            // 
            this.loadToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.loadDirectoryToolStripMenuItem});
            this.loadToolStripMenuItem.Name = "loadToolStripMenuItem";
            this.loadToolStripMenuItem.Size = new System.Drawing.Size(54, 24);
            this.loadToolStripMenuItem.Text = "Load";
            // 
            // loadDirectoryToolStripMenuItem
            // 
            this.loadDirectoryToolStripMenuItem.Name = "loadDirectoryToolStripMenuItem";
            this.loadDirectoryToolStripMenuItem.Size = new System.Drawing.Size(199, 26);
            this.loadDirectoryToolStripMenuItem.Text = "Load Directory";
            this.loadDirectoryToolStripMenuItem.Click += new System.EventHandler(this.loadDirectoryToolStripMenuItem_Click);
            // 
            // openFileDialog1
            // 
            this.openFileDialog1.FileName = "openFileDialog1";
            // 
            // groupBox_port_prPorts
            // 
            this.groupBox_port_prPorts.Controls.Add(this.listBox_port_prPorts);
            this.groupBox_port_prPorts.Controls.Add(this.label2);
            this.groupBox_port_prPorts.Location = new System.Drawing.Point(309, 6);
            this.groupBox_port_prPorts.Name = "groupBox_port_prPorts";
            this.groupBox_port_prPorts.Size = new System.Drawing.Size(248, 552);
            this.groupBox_port_prPorts.TabIndex = 1;
            this.groupBox_port_prPorts.TabStop = false;
            this.groupBox_port_prPorts.Text = "P-R";
            this.groupBox_port_prPorts.Visible = false;
            // 
            // listBox_port_prPorts
            // 
            this.listBox_port_prPorts.FormattingEnabled = true;
            this.listBox_port_prPorts.ItemHeight = 18;
            this.listBox_port_prPorts.Location = new System.Drawing.Point(3, 83);
            this.listBox_port_prPorts.Name = "listBox_port_prPorts";
            this.listBox_port_prPorts.Size = new System.Drawing.Size(239, 436);
            this.listBox_port_prPorts.TabIndex = 0;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(66, 525);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(49, 18);
            this.label2.TabIndex = 6;
            this.label2.Text = "Total: ";
            this.label2.Visible = false;
            // 
            // groupBox2_port_commonPorts
            // 
            this.groupBox2_port_commonPorts.Controls.Add(this.label3);
            this.groupBox2_port_commonPorts.Controls.Add(this.listBox_port_commonPorts);
            this.groupBox2_port_commonPorts.Location = new System.Drawing.Point(577, 6);
            this.groupBox2_port_commonPorts.Name = "groupBox2_port_commonPorts";
            this.groupBox2_port_commonPorts.Size = new System.Drawing.Size(242, 548);
            this.groupBox2_port_commonPorts.TabIndex = 2;
            this.groupBox2_port_commonPorts.TabStop = false;
            this.groupBox2_port_commonPorts.Text = "Ports";
            this.groupBox2_port_commonPorts.Visible = false;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(62, 524);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(49, 18);
            this.label3.TabIndex = 2;
            this.label3.Text = "Total: ";
            // 
            // listBox_port_commonPorts
            // 
            this.listBox_port_commonPorts.FormattingEnabled = true;
            this.listBox_port_commonPorts.ItemHeight = 18;
            this.listBox_port_commonPorts.Location = new System.Drawing.Point(6, 83);
            this.listBox_port_commonPorts.Name = "listBox_port_commonPorts";
            this.listBox_port_commonPorts.Size = new System.Drawing.Size(230, 436);
            this.listBox_port_commonPorts.TabIndex = 1;
            // 
            // groupBox_signal_srPorts
            // 
            this.groupBox_signal_srPorts.Controls.Add(this.label6);
            this.groupBox_signal_srPorts.Controls.Add(this.listBox_signal_srPorts);
            this.groupBox_signal_srPorts.Location = new System.Drawing.Point(326, 7);
            this.groupBox_signal_srPorts.Name = "groupBox_signal_srPorts";
            this.groupBox_signal_srPorts.Size = new System.Drawing.Size(273, 543);
            this.groupBox_signal_srPorts.TabIndex = 3;
            this.groupBox_signal_srPorts.TabStop = false;
            this.groupBox_signal_srPorts.Text = "S-R-Ports";
            this.groupBox_signal_srPorts.Visible = false;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(87, 518);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(45, 18);
            this.label6.TabIndex = 2;
            this.label6.Text = "Total:";
            // 
            // listBox_signal_srPorts
            // 
            this.listBox_signal_srPorts.FormattingEnabled = true;
            this.listBox_signal_srPorts.ItemHeight = 18;
            this.listBox_signal_srPorts.Location = new System.Drawing.Point(6, 66);
            this.listBox_signal_srPorts.Name = "listBox_signal_srPorts";
            this.listBox_signal_srPorts.Size = new System.Drawing.Size(261, 436);
            this.listBox_signal_srPorts.TabIndex = 0;
            // 
            // groupBox_signal_dataElement
            // 
            this.groupBox_signal_dataElement.Controls.Add(this.label7);
            this.groupBox_signal_dataElement.Controls.Add(this.listBox_signal_dataElement);
            this.groupBox_signal_dataElement.Location = new System.Drawing.Point(623, 7);
            this.groupBox_signal_dataElement.Name = "groupBox_signal_dataElement";
            this.groupBox_signal_dataElement.Size = new System.Drawing.Size(237, 538);
            this.groupBox_signal_dataElement.TabIndex = 4;
            this.groupBox_signal_dataElement.TabStop = false;
            this.groupBox_signal_dataElement.Text = "Data Elements";
            this.groupBox_signal_dataElement.Visible = false;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(62, 511);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(45, 18);
            this.label7.TabIndex = 2;
            this.label7.Text = "Total:";
            // 
            // listBox_signal_dataElement
            // 
            this.listBox_signal_dataElement.FormattingEnabled = true;
            this.listBox_signal_dataElement.ItemHeight = 18;
            this.listBox_signal_dataElement.Location = new System.Drawing.Point(0, 63);
            this.listBox_signal_dataElement.Name = "listBox_signal_dataElement";
            this.listBox_signal_dataElement.Size = new System.Drawing.Size(231, 436);
            this.listBox_signal_dataElement.TabIndex = 0;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(65, 530);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(49, 18);
            this.label1.TabIndex = 5;
            this.label1.Text = "Total: ";
            this.label1.Visible = false;
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // groupBox_port_component
            // 
            this.groupBox_port_component.Controls.Add(this.listBox_port_component);
            this.groupBox_port_component.Controls.Add(this.label1);
            this.groupBox_port_component.Location = new System.Drawing.Point(0, 6);
            this.groupBox_port_component.Name = "groupBox_port_component";
            this.groupBox_port_component.Size = new System.Drawing.Size(290, 555);
            this.groupBox_port_component.TabIndex = 7;
            this.groupBox_port_component.TabStop = false;
            this.groupBox_port_component.Text = "Component";
            this.groupBox_port_component.Visible = false;
            this.groupBox_port_component.Enter += new System.EventHandler(this.groupBox_port_component_Enter);
            // 
            // listBox_port_component
            // 
            this.listBox_port_component.FormattingEnabled = true;
            this.listBox_port_component.ItemHeight = 18;
            this.listBox_port_component.Location = new System.Drawing.Point(6, 84);
            this.listBox_port_component.Name = "listBox_port_component";
            this.listBox_port_component.Size = new System.Drawing.Size(274, 436);
            this.listBox_port_component.TabIndex = 0;
            this.listBox_port_component.SelectedValueChanged += new System.EventHandler(this.listBox_port_component_SelectedValueChanged);
            // 
            // port
            // 
            this.port.Controls.Add(this.tabPagePort);
            this.port.Controls.Add(this.tabPageSignal);
            this.port.Location = new System.Drawing.Point(132, 31);
            this.port.Name = "port";
            this.port.SelectedIndex = 0;
            this.port.Size = new System.Drawing.Size(1177, 597);
            this.port.TabIndex = 8;
            // 
            // tabPagePort
            // 
            this.tabPagePort.Controls.Add(this.groupBox_port_shared_component);
            this.tabPagePort.Controls.Add(this.groupBox_port_component);
            this.tabPagePort.Controls.Add(this.groupBox_port_prPorts);
            this.tabPagePort.Controls.Add(this.groupBox2_port_commonPorts);
            this.tabPagePort.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tabPagePort.Location = new System.Drawing.Point(4, 25);
            this.tabPagePort.Name = "tabPagePort";
            this.tabPagePort.Padding = new System.Windows.Forms.Padding(3);
            this.tabPagePort.Size = new System.Drawing.Size(1169, 568);
            this.tabPagePort.TabIndex = 0;
            this.tabPagePort.Text = "Port<..>Port";
            this.tabPagePort.UseVisualStyleBackColor = true;
            // 
            // groupBox_port_shared_component
            // 
            this.groupBox_port_shared_component.Controls.Add(this.label4);
            this.groupBox_port_shared_component.Controls.Add(this.listBox_port_shared_component);
            this.groupBox_port_shared_component.Location = new System.Drawing.Point(842, 6);
            this.groupBox_port_shared_component.Name = "groupBox_port_shared_component";
            this.groupBox_port_shared_component.Size = new System.Drawing.Size(290, 552);
            this.groupBox_port_shared_component.TabIndex = 8;
            this.groupBox_port_shared_component.TabStop = false;
            this.groupBox_port_shared_component.Text = "Component";
            this.groupBox_port_shared_component.Visible = false;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(74, 525);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(49, 18);
            this.label4.TabIndex = 1;
            this.label4.Text = "Total: ";
            // 
            // listBox_port_shared_component
            // 
            this.listBox_port_shared_component.FormattingEnabled = true;
            this.listBox_port_shared_component.ItemHeight = 18;
            this.listBox_port_shared_component.Location = new System.Drawing.Point(6, 81);
            this.listBox_port_shared_component.Name = "listBox_port_shared_component";
            this.listBox_port_shared_component.Size = new System.Drawing.Size(274, 436);
            this.listBox_port_shared_component.TabIndex = 0;
            // 
            // tabPageSignal
            // 
            this.tabPageSignal.Controls.Add(this.groupBox_signal_signal);
            this.tabPageSignal.Controls.Add(this.groupBox_signal_component);
            this.tabPageSignal.Controls.Add(this.groupBox_signal_srPorts);
            this.tabPageSignal.Controls.Add(this.groupBox_signal_dataElement);
            this.tabPageSignal.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tabPageSignal.Location = new System.Drawing.Point(4, 25);
            this.tabPageSignal.Name = "tabPageSignal";
            this.tabPageSignal.Padding = new System.Windows.Forms.Padding(3);
            this.tabPageSignal.Size = new System.Drawing.Size(1169, 568);
            this.tabPageSignal.TabIndex = 1;
            this.tabPageSignal.Text = "Signal";
            this.tabPageSignal.UseVisualStyleBackColor = true;
            // 
            // groupBox_signal_signal
            // 
            this.groupBox_signal_signal.Controls.Add(this.listBox_signal_signal);
            this.groupBox_signal_signal.Location = new System.Drawing.Point(881, 7);
            this.groupBox_signal_signal.Name = "groupBox_signal_signal";
            this.groupBox_signal_signal.Size = new System.Drawing.Size(237, 537);
            this.groupBox_signal_signal.TabIndex = 9;
            this.groupBox_signal_signal.TabStop = false;
            this.groupBox_signal_signal.Text = "Signals";
            this.groupBox_signal_signal.Visible = false;
            // 
            // listBox_signal_signal
            // 
            this.listBox_signal_signal.FormattingEnabled = true;
            this.listBox_signal_signal.ItemHeight = 18;
            this.listBox_signal_signal.Location = new System.Drawing.Point(0, 61);
            this.listBox_signal_signal.Name = "listBox_signal_signal";
            this.listBox_signal_signal.Size = new System.Drawing.Size(231, 436);
            this.listBox_signal_signal.TabIndex = 0;
            // 
            // groupBox_signal_component
            // 
            this.groupBox_signal_component.Controls.Add(this.label5);
            this.groupBox_signal_component.Controls.Add(this.listBox_signal_component);
            this.groupBox_signal_component.Location = new System.Drawing.Point(6, 6);
            this.groupBox_signal_component.Name = "groupBox_signal_component";
            this.groupBox_signal_component.Size = new System.Drawing.Size(307, 547);
            this.groupBox_signal_component.TabIndex = 8;
            this.groupBox_signal_component.TabStop = false;
            this.groupBox_signal_component.Text = "Component";
            this.groupBox_signal_component.Visible = false;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(96, 521);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(45, 18);
            this.label5.TabIndex = 1;
            this.label5.Text = "Total:";
            // 
            // listBox_signal_component
            // 
            this.listBox_signal_component.FormattingEnabled = true;
            this.listBox_signal_component.ItemHeight = 18;
            this.listBox_signal_component.Location = new System.Drawing.Point(6, 68);
            this.listBox_signal_component.Name = "listBox_signal_component";
            this.listBox_signal_component.Size = new System.Drawing.Size(283, 436);
            this.listBox_signal_component.TabIndex = 0;
            this.listBox_signal_component.SelectedValueChanged += new System.EventHandler(this.listBox_signal_component_SelectedValueChanged);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1321, 630);
            this.Controls.Add(this.port);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Form1";
            this.Text = "Relation Finder";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.groupBox_port_prPorts.ResumeLayout(false);
            this.groupBox_port_prPorts.PerformLayout();
            this.groupBox2_port_commonPorts.ResumeLayout(false);
            this.groupBox2_port_commonPorts.PerformLayout();
            this.groupBox_signal_srPorts.ResumeLayout(false);
            this.groupBox_signal_srPorts.PerformLayout();
            this.groupBox_signal_dataElement.ResumeLayout(false);
            this.groupBox_signal_dataElement.PerformLayout();
            this.groupBox_port_component.ResumeLayout(false);
            this.groupBox_port_component.PerformLayout();
            this.port.ResumeLayout(false);
            this.tabPagePort.ResumeLayout(false);
            this.groupBox_port_shared_component.ResumeLayout(false);
            this.groupBox_port_shared_component.PerformLayout();
            this.tabPageSignal.ResumeLayout(false);
            this.groupBox_signal_signal.ResumeLayout(false);
            this.groupBox_signal_component.ResumeLayout(false);
            this.groupBox_signal_component.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem loadToolStripMenuItem;
        private System.Windows.Forms.OpenFileDialog openFileDialog1;
        private System.Windows.Forms.GroupBox groupBox_port_prPorts;
        private System.Windows.Forms.GroupBox groupBox2_port_commonPorts;
        private System.Windows.Forms.GroupBox groupBox_signal_srPorts;
        private System.Windows.Forms.GroupBox groupBox_signal_dataElement;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ListBox listBox_port_prPorts;
        private System.Windows.Forms.ListBox listBox_port_commonPorts;
        private System.Windows.Forms.ListBox listBox_signal_srPorts;
        private System.Windows.Forms.ListBox listBox_signal_dataElement;
        private System.Windows.Forms.GroupBox groupBox_port_component;
        private System.Windows.Forms.ListBox listBox_port_component;
        private System.Windows.Forms.ToolStripMenuItem loadDirectoryToolStripMenuItem;
        private System.Windows.Forms.TabControl port;
        private System.Windows.Forms.TabPage tabPagePort;
        private System.Windows.Forms.TabPage tabPageSignal;
        private System.Windows.Forms.GroupBox groupBox_signal_component;
        private System.Windows.Forms.ListBox listBox_signal_component;
        private System.Windows.Forms.GroupBox groupBox_signal_signal;
        private System.Windows.Forms.ListBox listBox_signal_signal;
        private System.Windows.Forms.GroupBox groupBox_port_shared_component;
        private System.Windows.Forms.ListBox listBox_port_shared_component;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label5;
    }
}

