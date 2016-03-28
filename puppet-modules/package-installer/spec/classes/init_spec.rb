require 'spec_helper'
describe 'installer' do

  context 'with defaults for all parameters' do
    it { should contain_class('installer') }
  end
end
