#![cfg_attr(not(debug_assertions), windows_subsystem = "windows")] // hide console window

use native_dialog::MessageDialog;
use native_dialog::MessageType;
use eframe::egui;

fn main() -> eframe::Result {
    let options = eframe::NativeOptions {
        viewport: egui::ViewportBuilder::default()
            .with_inner_size([1280.0, 1024.0]),
            ..Default::default()
    };

    eframe::run_native("TextHunter", options, Box::new(|_cc| {
        Ok(Box::<TextHunter>::default())
    }),
        )
}

struct TextHunter {
    state_txt_search_string: String,
    state_chk_search_filecontents: bool,
    state_chk_search_filenames: bool,
    state_chk_regex_search: bool,
    state_chk_case_sensitive: bool,

    state_txt_search_path: String,
    state_chk_subdirs: bool,

    state_chk_filtered_search: bool,
    state_chk_regex_filter: bool,
    state_txt_filter: String,

}

impl Default for TextHunter {
    fn default() -> Self {
        Self {
            state_txt_search_string: "".to_owned(),
            state_chk_search_filecontents: true,
            state_chk_search_filenames: true,
            state_chk_regex_search: false,
            state_chk_case_sensitive: false,

            state_txt_search_path: "".to_owned(),
            state_chk_subdirs: true,

            state_chk_filtered_search: false,
            state_chk_regex_filter: false,
            state_txt_filter: "".to_owned(),
        }
    }
}

impl eframe::App for TextHunter {
    fn update(&mut self, ctx: &egui::Context, _frame: &mut eframe::Frame) {
        egui::CentralPanel::default().show(ctx, |ui| {
            ui.horizontal(|ui| {
                let resp_lbl_name = ui.label("Search for:");
                ui.text_edit_singleline(&mut self.state_txt_search_string)
                    .labelled_by(resp_lbl_name.id)
                    .on_hover_text("the string to search for");
                ui.checkbox(&mut self.state_chk_search_filecontents, "contents?")
                    .on_hover_text("check if file contents should be searched");
                ui.checkbox(&mut self.state_chk_search_filenames, "names?")
                    .on_hover_text("check if file names should be searched");
                ui.separator();
                ui.checkbox(&mut self.state_chk_regex_search, "regex?")
                    .on_hover_text("check if search string should be treated as a regular expression");
                ui.separator();
                ui.checkbox(&mut self.state_chk_case_sensitive, "case-sensitive?")
                    .on_hover_text("check if search string character case should be matched");
                ui.separator();
                let resp_btn_search = ui.add(egui::Button::new("Search!"))
                    .on_hover_text("launch the search; all input fields will be cleared");

                if resp_btn_search.clicked() {
                    MessageDialog::new()
                        .set_type(MessageType::Info)
                        .set_title("Todo: implement search")
                        .set_text("implement search!")
                        .show_alert()
                        .unwrap();
                }
            });

            ui.horizontal(|ui| {
                let resp_lbl_name = ui.label("In Path:");
                ui.add_enabled(false, egui::TextEdit::singleline(&mut self.state_txt_search_path))
                    .labelled_by(resp_lbl_name.id)
                    .on_hover_text("path in which to search; update this path by clicking on the browse... button");
                ui.checkbox(&mut self.state_chk_subdirs, "subdirs?")
                    .on_hover_text("check if subdirectories should also be searched");
                ui.separator();
                let resp_btn_browse = ui.add(egui::Button::new("Browse..."))
                    .on_hover_text("select a path to search within");

                if resp_btn_browse.clicked() {
                    MessageDialog::new()
                            .set_type(MessageType::Info)
                            .set_title("Todo: implement browse")
                            .set_text("implement browse!")
                            .show_alert()
                            .unwrap();
                }
            });

            ui.horizontal(|ui| {
                ui.checkbox(&mut self.state_chk_filtered_search, "filtered search?").on_hover_text("check if the search should be filtered");

                if self.state_chk_filtered_search {
                    ui.separator();
                    ui.checkbox(&mut self.state_chk_regex_filter, "regex?").on_hover_text("check if the filter should be interpreted as a regular expression");
                    ui.text_edit_singleline(&mut self.state_txt_filter).on_hover_text("text to filter the search by");
                }
            });

            ui.add(egui::Separator::default());
        });
    }
}
