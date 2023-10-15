definition(
    name: "Vacation Lighting",
    namespace: "tkreiner",
    author: "Tom Kreiner",
    description: "Manage random lighting schedules while on vacation",
    category: "Convenience",
    iconUrl: "",
    iconX2Url: "",
    documentationLink: "https://github.com/tkreiner/hubitat_vacation_lighting",
    importUrl: "https://raw.githubusercontent.com/tkreiner/hubitat_vacation_lighting/main/app/hubitat_vacation_lighting.groovy"
)

preferences {
    page(name: "mainPage", title: "Vacation Lighting", install: true, uninstall: true)
    page(name: "configSwitch", title: "Configure Switch", uninstall: true)
}

// Define the main page of the app
def mainPage() {
	dynamicPage(name: "mainPage", title: "Mode Switches", uninstall: true, install: true) {
        section {
            paragraph "Welcome to the Vacation Lighting app."
            paragraph "Select the mode that will be used to activate this lighting."
            input "modes", "mode", title: "Select Modes", multiple: true, submitOnChange: true, required: true
            paragraph "Select the switches that you want to control."
            input "switches", "capability.switch", title: "Select Switches", multiple: true, submitOnChange: true, required: true
            if(switches) {
                switches.each{dev ->
                    paragraph dev.getLabel()
                    href name: "configSwitch", page: "configSwitch", title: "Setup Schedule", params: [deviceId: dev.id]
                }
            }
        }
	}
}

// Define page that sets up lighting for a switch
def configSwitch(params) {
    dynamicPage(name: "configSwitch", title: "Configure Switch", uninstall: true) {
        section {
            paragraph "Configure lighting schedule for ${params.deviceId}"
            href name: "returnMain", page: "mainPage", title: "Go to Setup Page"
        }
    }
}

// Called when app first installed
def installed() {
  // for now, just write entry to "Logs" when it happens:
    log.trace "installed()"
    updated()
}

// Called when user presses "Done" button in app
def updated() {
    log.trace "updated()"
    subscribe(location, modeHandler)
}

// Called when app uninstalled
def uninstalled() {
   log.trace "uninstalled()"
   // Most apps would not need to do anything here
}

// Detect when mode has changed
def modeHandler(evt) {
    log.debug "modeHandler() called: ${evt.name} ${evt.value}"
    if (modes.contains(evt.value)) {
        log.debug "Enabling vacation lighting."
    } else {
        log.debug "Disabling vacation lighting."
    }
}
