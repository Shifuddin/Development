require 'spec_helper'
describe 'currentusers' do

  context 'with defaults for all parameters' do
    it { should contain_class('currentusers') }
  end
end
